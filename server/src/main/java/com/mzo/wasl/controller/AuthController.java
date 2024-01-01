package com.mzo.wasl.controller;

import com.mzo.wasl.model.*;
import com.mzo.wasl.dto.request.LoginRequest;
import com.mzo.wasl.dto.request.SignupRequest;
import com.mzo.wasl.dto.response.JwtResponse;
import com.mzo.wasl.dto.response.MessageResponse;
import com.mzo.wasl.repository.*;
import com.mzo.wasl.security.jwt.JwtUtils;
import com.mzo.wasl.security.services.UserDetailsImpl;
import com.mzo.wasl.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    ProfileService profileService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    SenderService senderService;
    @Autowired
    TravelerService travelerService;

    @Autowired
    OfferRepository offerRepository;
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        String username=null;
        if (userService.existsByEmail(loginRequest.getEmail())){
            Optional<User> user = userService.getUserByEmail(loginRequest.getEmail());
            username=user.get().getUsername();
        } else if (userService.existsByUsername(loginRequest.getEmail())) {
            username=loginRequest.getEmail();
        }else {
            return ResponseEntity.ok(new MessageResponse("No account exists with the provided email address/username"));
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles.toString()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Role userRole = roleService.getRoleByName(ERole.ROLE_REGULAR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        user.setRole(userRole);

        Profile profile = new Profile(user);
        userService.addUser(user);
        profileService.addProfile(profile);
        Sender sender =new Sender(user);
        senderService.addSender(sender);
        Traveler traveler =new Traveler(user);
        travelerService.addTraveler(traveler);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}

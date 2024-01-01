package com.mzo.wasl.controller;

import com.mzo.wasl.dto.request.SupportRequest;
import com.mzo.wasl.dto.response.MessageResponse;
import com.mzo.wasl.model.*;
import com.mzo.wasl.service.RoleService;
import com.mzo.wasl.service.SupportService;
import com.mzo.wasl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class SupportController {
    @Autowired
    SupportService supportService;
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    RoleService roleService;
    @GetMapping("/supports")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllSupports() {
        return ResponseEntity.ok(supportService.getAllSupports());
    }

    // Create new support
    @PostMapping("/supports")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createSupport(@RequestBody SupportRequest support) {
        if (userService.existsByUsername(support.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userService.existsByEmail(support.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(support.getUsername(),
                support.getEmail(),
                encoder.encode(support.getPassword()));

        Role userRole = roleService.getRoleByName(ERole.ROLE_SUPPORT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        user.setRole(userRole);

        userService.addUser(user);
        supportService.createSupport(new Support(user));
        return ResponseEntity.ok(new MessageResponse("Client Support registered successfully!"));
    }

    // Delete support
    @DeleteMapping("/supports/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteSupport(@PathVariable Long id) {
        Optional<Support> support = supportService.getSupportById(id);
        if (!support.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Support not found!"));
        }
        User user = support.get().getUser();
        supportService.deleteSupport(id);
        userService.deleteUser(user.getId());
        return ResponseEntity.ok(new MessageResponse("Support deleted successfully!"));
    }

    // Update support
    @PutMapping("/supports/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateSupport(@PathVariable Long id, @RequestBody SupportRequest supportRequest) {
        Optional<Support> support = supportService.getSupportById(id);
        if (!support.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Support not found!"));
        }
        User user = support.get().getUser();
        user.setUsername(supportRequest.getUsername());
        user.setEmail(supportRequest.getEmail());
        user.setPassword(encoder.encode(supportRequest.getPassword()));
        userService.addUser(user);
        return ResponseEntity.ok(new MessageResponse("Support updated successfully!"));
    }

    // Get support by id
    @GetMapping("/supports/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getSupportById(@PathVariable Long id) {
        Optional<Support> support = supportService.getSupportById(id);
        if (!support.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Support not found!"));
        }
        return ResponseEntity.ok(support.get().getUser());
    }
}
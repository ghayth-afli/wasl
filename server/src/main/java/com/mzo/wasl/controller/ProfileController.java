package com.mzo.wasl.controller;

import com.mzo.wasl.model.Profile;
import com.mzo.wasl.dto.request.ProfileRequest;
import com.mzo.wasl.dto.response.ProfileResponse;
import com.mzo.wasl.repository.ProfileRepository;
import com.mzo.wasl.security.services.UserDetailsImpl;
import com.mzo.wasl.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/myprofile")
    @PreAuthorize("hasRole('REGULAR')")
    public ResponseEntity<?> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Profile profile = profileService.getProfileByUserId(user.getId());
        return ResponseEntity.ok(profile);
        // return ResponseEntity.ok(new
        // ProfileResponse(profile.getId(),profile.getFirstName(),profile.getLastName(),profile.getBio(),profile.getCountry(),profile.getCity(),profile.getPhoneNumber(),profile.getLanguage()
        // ,profile.getImage()));
    }

    @PutMapping("/myprofile")
    @PreAuthorize("hasRole('REGULAR')")
    public ResponseEntity<?> UpdateProfile(@RequestBody ProfileRequest profileRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Profile profile = profileService.getProfileByUserId(user.getId());
        profile.setFirstName(profileRequest.getFirstName());
        profile.setLastName(profileRequest.getLastName());
        profile.setBio(profileRequest.getBio());
        profile.setCountry(profileRequest.getCountry());
        profile.setCity(profileRequest.getCity());
        profile.setPhoneNumber(profileRequest.getPhoneNumber());
        profile.setLanguage(profileRequest.getLanguage());
        profile.setImage(profileRequest.getImage());
        profileService.updateProfile(profile);
        return ResponseEntity.ok(new ProfileResponse(profile.getId(), profile.getFirstName(), profile.getLastName(),
                profile.getBio(), profile.getCountry(), profile.getCity(), profile.getPhoneNumber(),
                profile.getLanguage(), profile.getImage()));
    }

}

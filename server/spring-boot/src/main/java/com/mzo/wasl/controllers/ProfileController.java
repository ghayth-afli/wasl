package com.mzo.wasl.controllers;

import com.mzo.wasl.models.Profile;
import com.mzo.wasl.payload.request.ProfileRequest;
import com.mzo.wasl.payload.response.ProfileResponse;
import com.mzo.wasl.repositories.ProfileRepository;
import com.mzo.wasl.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProfileController {
    @Autowired
    private ProfileRepository profileRepository;
    @GetMapping("/myprofile")
    @PreAuthorize("hasRole('REGULAR')")
    public ResponseEntity<?> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Profile profile =profileRepository.findByUserId(user.getId());
        return ResponseEntity.ok(user);
        //return ResponseEntity.ok(new ProfileResponse(profile.getId(),profile.getFirstName(),profile.getLastName(),profile.getBio(),profile.getCountry(),profile.getCity(),profile.getPhoneNumber(),profile.getLanguage() ,profile.getImage()));
    }


    @PutMapping("/myprofile")
    @PreAuthorize("hasRole('REGULAR')")
    public ResponseEntity<?> UpdateProfile(@RequestBody ProfileRequest profileRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Profile profile =profileRepository.findByUserId(user.getId());
        profile.setFirstName(profileRequest.getFirstName());
        profile.setLastName(profileRequest.getLastName());
        profile.setBio(profileRequest.getBio());
        profile.setCountry(profileRequest.getCountry());
        profile.setCity(profileRequest.getCity());
        profile.setPhoneNumber(profileRequest.getPhoneNumber());
        profile.setLanguage(profileRequest.getLanguage());
        profile.setImage(profileRequest.getImage());
        profileRepository.save(profile);
        return ResponseEntity.ok(new ProfileResponse(profile.getId(),profile.getFirstName(),profile.getLastName(),profile.getBio(),profile.getCountry(),profile.getCity(),profile.getPhoneNumber(),profile.getLanguage() ,profile.getImage()));
    }

}

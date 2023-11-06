package com.mzo.wasl.controllers;

import com.mzo.wasl.models.Profile;
import com.mzo.wasl.payload.response.MessageResponse;
import com.mzo.wasl.repositories.ProfileRepository;
import com.mzo.wasl.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class SwitchController {
    @Autowired
    ProfileRepository profileRepository;

    @PutMapping("/switch")
    @PreAuthorize("hasRole('REGULAR')")
    public ResponseEntity<?> switchTo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Profile profile =profileRepository.findByUserId(user.getId());
        profile.setIsTraveler(!profile.isTraveler());
        profileRepository.save(profile);
        return ResponseEntity.ok(new MessageResponse(profile.isTraveler()+""));
    }
}

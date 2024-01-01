package com.mzo.wasl.controller;

import com.mzo.wasl.dto.response.MessageResponse;
import com.mzo.wasl.model.Profile;
import com.mzo.wasl.security.services.UserDetailsImpl;
import com.mzo.wasl.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class SwitchController {
    @Autowired
    ProfileService profileService;

    @PutMapping("/switch")
    @PreAuthorize("hasRole('REGULAR')")
    public ResponseEntity<?> switchTo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Profile profile =profileService.getProfileByUserId(user.getId());
        profile.setIsTraveler(!profile.isTraveler());
        profileService.updateProfile(profile);
        return ResponseEntity.ok(new MessageResponse(profile.isTraveler()+""));
    }
}

package com.mzo.wasl.controller;

import com.mzo.wasl.dto.response.RegularWithProfileResponse;
import com.mzo.wasl.model.Profile;
import com.mzo.wasl.model.User;
import com.mzo.wasl.service.ProfileService;
import com.mzo.wasl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class RegularController {
    @Autowired
    UserService userService;
    @Autowired
    ProfileService profileService;
    @GetMapping("/regulars")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllRegulars() {
        List<Profile> profiles = profileService.getAllProfiles();
        RegularWithProfileResponse regularWithProfileResponse = new RegularWithProfileResponse();
        List<RegularWithProfileResponse> regulars = profiles.stream().map((p) -> {
            regularWithProfileResponse.setId(p.getUser().getId());
            regularWithProfileResponse.setUsername(p.getUser().getUsername());
            regularWithProfileResponse.setEmail(p.getUser().getEmail());
            regularWithProfileResponse.setFirstName(p.getFirstName());
            regularWithProfileResponse.setLastName(p.getLastName());
            regularWithProfileResponse.setBio(p.getBio());
            regularWithProfileResponse.setCountry(p.getCountry());
            regularWithProfileResponse.setCity(p.getCity());
            regularWithProfileResponse.setPhoneNumber(p.getPhoneNumber());
            regularWithProfileResponse.setLanguage(p.getLanguage());
            regularWithProfileResponse.setImage(p.getImage());
            return regularWithProfileResponse;
        }).toList();
        return ResponseEntity.ok(regulars);
    }
}

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
import java.util.stream.Collectors;

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
        List<RegularWithProfileResponse> regulars = profiles.stream()
                .map(p -> {
                    RegularWithProfileResponse response = new RegularWithProfileResponse();
                    response.setId(p.getUser().getId());
                    response.setUsername(p.getUser().getUsername());
                    response.setEmail(p.getUser().getEmail());
                    response.setFirstName(p.getFirstName());
                    response.setLastName(p.getLastName());
                    response.setBio(p.getBio());
                    response.setCountry(p.getCountry());
                    response.setCity(p.getCity());
                    response.setPhoneNumber(p.getPhoneNumber());
                    response.setLanguage(p.getLanguage());
                    response.setImage(p.getImage());
                    return response;
                }).toList();
        return ResponseEntity.ok(regulars);
    }
}

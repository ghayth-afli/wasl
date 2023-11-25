package com.mzo.wasl.security.services;

import com.mzo.wasl.model.Profile;
import com.mzo.wasl.repository.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("securityService")
public class SecurityService {
    @Autowired
    ProfileRepository profileRepository;
    Logger logger = LoggerFactory.getLogger(SecurityService.class);
    Authentication authentication;
    public boolean isTraveler(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        Profile profile = profileRepository.findByUserId(user.getId());
        return profile.isTraveler();
    }
}

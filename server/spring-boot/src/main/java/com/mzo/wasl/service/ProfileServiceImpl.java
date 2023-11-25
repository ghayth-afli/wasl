package com.mzo.wasl.service;

import com.mzo.wasl.model.Profile;
import com.mzo.wasl.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService{
    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public Profile getProfileByUserId(Long id) {
        return profileRepository.findByUserId(id);
    }
    @Override
    public void updateProfile(Profile profile) {
        profileRepository.save(profile);
    }

    @Override
    public void addProfile(Profile profile) {
        profileRepository.save(profile);
    }
}

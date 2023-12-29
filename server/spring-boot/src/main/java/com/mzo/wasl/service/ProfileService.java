package com.mzo.wasl.service;

import com.mzo.wasl.model.Profile;

import java.util.List;

public interface ProfileService {
    Profile getProfileByUserId(Long id);
    void updateProfile(Profile profile);
    void addProfile(Profile profile);
    List<Profile> getAllProfiles();
}

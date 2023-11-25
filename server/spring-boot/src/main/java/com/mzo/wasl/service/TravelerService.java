package com.mzo.wasl.service;

import com.mzo.wasl.model.Traveler;

import java.util.List;

public interface TravelerService {
    List<Traveler> getAllTravelers();
    Traveler getTravelerByUserId(Long id);
    void addTraveler(Traveler traveler);
    void updateTraveler(Traveler traveler);
    void deleteTraveler(Long id);
}

package com.mzo.wasl.service;

import com.mzo.wasl.model.Traveler;
import com.mzo.wasl.repository.TravelerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelerServiceImpl implements TravelerService{
    @Autowired
    TravelerRepository travelerRepository;

    @Override
    public List<Traveler> getAllTravelers() {
        return travelerRepository.findAll();
    }

    @Override
    public Traveler getTravelerByUserId(Long id) {
        return travelerRepository.findByUserId(id);
    }

    @Override
    public void addTraveler(Traveler traveler) {
        travelerRepository.save(traveler);
    }

    @Override
    public void updateTraveler(Traveler traveler) {
        travelerRepository.save(traveler);
    }

    @Override
    public void deleteTraveler(Long id) {
        travelerRepository.deleteById(id);
    }
}

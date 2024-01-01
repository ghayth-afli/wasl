package com.mzo.wasl.service;

import com.mzo.wasl.model.Support;
import com.mzo.wasl.repository.SupportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupportServiceImpl implements SupportService{
    @Autowired
    SupportRepository supportRepository;
    @Override
    public Optional<Support> getSupportByUserId(Long userId) {
        return supportRepository.findByUserId(userId);
    }

    @Override
    public List<Support> getAllSupports() {
        return supportRepository.findAll();
    }

    @Override
    public void createSupport(Support support) {
        supportRepository.save(support);
    }

    @Override
    public void deleteSupport(Long id) {
        supportRepository.deleteById(id);
    }

    @Override
    public Optional<Support> getSupportById(Long id) {
        return supportRepository.findById(id);
    }
}

package com.mzo.wasl.service;

import com.mzo.wasl.model.Support;

import java.util.List;
import java.util.Optional;

public interface SupportService {
    Optional<Support> getSupportByUserId(Long userId);
    List<Support> getAllSupports();
    void createSupport(Support support);
    void deleteSupport(Long id);
    Optional<Support> getSupportById(Long id);
}

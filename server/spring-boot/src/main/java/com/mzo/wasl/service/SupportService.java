package com.mzo.wasl.service;

import com.mzo.wasl.model.Support;

import java.util.Optional;

public interface SupportService {
    Optional<Support> getSupportByUserId(Long userId);
}

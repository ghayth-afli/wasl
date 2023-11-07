package com.mzo.wasl.repositories;

import com.mzo.wasl.models.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelerRepository extends JpaRepository<Traveler,Long> {
}

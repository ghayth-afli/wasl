package com.mzo.wasl.repositories;

import com.mzo.wasl.models.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelerRepository extends JpaRepository<Traveler,Long> {
    Traveler findByUserId(Long id);
}

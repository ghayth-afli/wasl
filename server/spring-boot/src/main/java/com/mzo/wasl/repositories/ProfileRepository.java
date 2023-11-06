package com.mzo.wasl.repositories;

import com.mzo.wasl.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
    Profile findByUserId(Long id);
}

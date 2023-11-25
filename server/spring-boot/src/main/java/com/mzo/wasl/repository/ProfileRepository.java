package com.mzo.wasl.repository;

import com.mzo.wasl.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{
    Profile findByUserId(Long id);
}

package com.mzo.wasl.repository;

import com.mzo.wasl.model.Profile;
import com.mzo.wasl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
    Profile findByUserId(Long id);
}

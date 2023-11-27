package com.mzo.wasl.repository;

import com.mzo.wasl.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepositroy extends JpaRepository<Review, Long> {
}

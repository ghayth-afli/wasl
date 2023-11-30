package com.mzo.wasl.service;

import com.mzo.wasl.model.Review;
import com.mzo.wasl.repository.ReviewRepositroy;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    ReviewRepositroy reviewRepositroy;

    @Override
    public Optional<Review> getReview(Long id) {

       return reviewRepositroy.findById(id);
    }

    @Override
    public void addReview(Review r) {
        reviewRepositroy.save(r);
    }


    @Override
    public void deleteReview(Long id) {
      reviewRepositroy.deleteById(id);
    }

    @Override
    public Optional<Review> getReviewByRequestId(Long id) {
       return reviewRepositroy.findByRequestId(id);
    }
}

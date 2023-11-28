package com.mzo.wasl.service;

import java.util.List;
import java.util.Optional;
import com.mzo.wasl.model.Review;

public interface ReviewService {
     Optional<Review> getReview(Long id);
     void addReview(Review r);
     void updateReview(Review r);
     void deleteReview(Long id);
     List<Review>getReviewsByRequestId(Long id);
   

}

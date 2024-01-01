package com.mzo.wasl.controller;

import com.mzo.wasl.dto.request.ReviewRequest;
import com.mzo.wasl.dto.response.MessageResponse;
import com.mzo.wasl.model.Request;
import com.mzo.wasl.model.Review;
import com.mzo.wasl.model.Sender;
import com.mzo.wasl.model.Traveler;
import com.mzo.wasl.security.services.UserDetailsImpl;
import com.mzo.wasl.service.*;
import com.mzo.wasl.util.CurrentDateAndTimeUtilImpl;
import jakarta.validation.Valid;

import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class ReviewController {
    @Autowired
    ReviewService reviewService;
    @Autowired
    TravelerService travelerService;
    @Autowired
    OfferService offerService;
    @Autowired
    SenderService senderService;
    @Autowired
    RequestService requestService;
    @Autowired
    CurrentDateAndTimeUtilImpl currentDateAndTime;


    @PostMapping("/request/{requestId}/review")
    @PreAuthorize("hasRole('REGULAR') and !@securityService.isTraveler()")
    public ResponseEntity<?> addReview(@PathVariable Long requestId, @Valid @RequestBody ReviewRequest review) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Sender currentSender = senderService.getSenderByUserId(userDetails.getId());
        Optional<Request> request = requestService.getRequest(requestId);
        // check if the request exists
        if(!request.isPresent()){
            return ResponseEntity.ok(new MessageResponse("Request not found"));
        }
        // check if the sender is the owner of the request
        if(!currentSender.getRequests().contains(request.get())){
            return ResponseEntity.ok (new MessageResponse("This Request does not belong to you"));
        }
        // check if the sender already added a review to this request
        if(reviewService.getReviewByRequestId(request.get().getId()).isPresent()){
            return ResponseEntity.ok(new MessageResponse("You already added a review to this request"));
        }
        // add the review
        Review r = new Review(review.getComment(),
                review.getRating(),
                Date.from(currentDateAndTime.getCurrentLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                request.get());
        reviewService.addReview(r);
        return ResponseEntity.ok(new MessageResponse("Review added successfully"));
       
    }

    /*@GetMapping("/reviews/{id}")
    public ResponseEntity<?> getReviews(@PathVariable Long id) {
        if(!reviewService.getReview(id).isPresent()){
            return new ResponseEntity<>("Review not found",HttpStatus.NOT_FOUND);
        }
        return  ResponseEntity.ok(reviewService.getReview(id));
    }*/
    /*@DeleteMapping("/review/{id}")
    @PreAuthorize("hasRole('REGULAR') and @securityService.isTraveler()")
    public ResponseEntity<?> deleteOffer(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
         Sender currentSender = senderService.getSenderByUserId(userDetails.getId());
         if(!currentSender.getRequests().contains(reviewService.getReviewsByRequestId(id))){
            return ResponseEntity.ok(new MessageResponse("this review is not yours"));
         }
         reviewService.deleteReview(id);
         return  ResponseEntity.ok("review deleted successfully");
    }*/
}

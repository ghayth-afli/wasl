package com.mzo.wasl.controller;

import com.mzo.wasl.dto.request.ReviewRequest;
import com.mzo.wasl.dto.response.MessageResponse;
import com.mzo.wasl.model.Request;
import com.mzo.wasl.model.Review;
import com.mzo.wasl.model.Sender;
import com.mzo.wasl.model.Traveler;
import com.mzo.wasl.security.services.UserDetailsImpl;
import com.mzo.wasl.service.*;
import jakarta.validation.Valid;

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



    @PostMapping("/request/{requestId}/review")
    @PreAuthorize("hasRole('REGULAR') and !@securityService.isTraveler()")
    public ResponseEntity<?> addReview(@PathVariable Long requestId, @Valid @RequestBody ReviewRequest review) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Sender currentSender = senderService.getSenderByUserId(userDetails.getId());
        Optional<Request> request = requestService.getRequest(requestId);

        // check if the sender already added a review 
        if(currentSender.getRequests().contains(reviewService.getReview(requestId).get())){
            return ResponseEntity.ok(new MessageResponse("you already reviewed this request "));
        }
        //
        if(request.isPresent()){
             reviewService.addReview(
            new Review(review.getComment(), review.getRating(), review.getDate(),request ));
            return ResponseEntity.ok("Review added successfully");
        }
        return new ResponseEntity<>("Request not found",HttpStatus.NOT_FOUND);
        
       
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<?> getReviews(@PathVariable Long id) {
        if(!reviewService.getReview(id).isPresent()){
            return new ResponseEntity<>("Review not found",HttpStatus.NOT_FOUND);
        }
        return  ResponseEntity.ok(reviewService.getReview(id));
    }
    @DeleteMapping("/review/{id}")
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
    }
}

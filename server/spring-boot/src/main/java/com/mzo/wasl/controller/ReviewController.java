package com.mzo.wasl.controller;

import com.mzo.wasl.dto.request.ReviewRequest;
import com.mzo.wasl.dto.response.MessageResponse;
import com.mzo.wasl.model.Request;
import com.mzo.wasl.model.Review;
import com.mzo.wasl.model.Sender;
import com.mzo.wasl.security.services.UserDetailsImpl;
import com.mzo.wasl.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
        // 
        // if(currentSender.getRequest(requestId).contains(reviewService.getReview(requestId).get())){
        //     return ResponseEntity.ok(new MessageResponse("you already reviewed this request "));
        // }
        reviewService.addReview(
            new Review(review.getComment(), review.getRating(), review.getDate(),requestId ));
        return ResponseEntity.ok("Review added successfully");
    }
}

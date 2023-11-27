package com.mzo.wasl.controller;

import com.mzo.wasl.dto.request.ReviewRequest;
import com.mzo.wasl.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        //implementing the logic of adding a review.....
        //......
        return ResponseEntity.ok("Review added successfully");
    }
}

package com.mzo.wasl.controllers;

import com.mzo.wasl.models.Offer;
import com.mzo.wasl.payload.request.LoginRequest;
import com.mzo.wasl.payload.request.OfferRequest;
import com.mzo.wasl.payload.response.MessageResponse;
import com.mzo.wasl.repositories.OfferRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OfferController {
    @Autowired
    OfferRepository offerRepository;

    @GetMapping("/offers")
    @PreAuthorize("hasRole('REGULAR')")
    public ResponseEntity<?> getAllOffers() {
        return ResponseEntity.ok(offerRepository.findAll());
    }

    @GetMapping("/offers/{id}")
    @PreAuthorize("hasRole('REGULAR')")
    public ResponseEntity<?> getOffer(@PathVariable Long id) {
        return ResponseEntity.ok(offerRepository.findById(id));
    }

    @PostMapping("/offers")
    @PreAuthorize("hasRole('REGULAR') and @securityService.isTraveler()")
    public ResponseEntity<?> addOffer(@Valid @RequestBody OfferRequest offerRequest) {
        offerRepository.save(new Offer(offerRequest.getTitle(),
                offerRequest.getDescription(),
                offerRequest.getDepart(),
                offerRequest.getDestination(),
                offerRequest.getDate(),
                offerRequest.getTime(),
                offerRequest.getPrice(),
                offerRequest.getCapacity(),
                offerRequest.getCapacity(),
                offerRequest.getImage()));
        return ResponseEntity.ok(new MessageResponse("Offer added successfully"));
    }

}

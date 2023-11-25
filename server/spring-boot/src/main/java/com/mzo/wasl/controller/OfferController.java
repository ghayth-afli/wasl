package com.mzo.wasl.controller;

import com.mzo.wasl.model.Offer;
import com.mzo.wasl.model.Traveler;
import com.mzo.wasl.dto.request.OfferRequest;
import com.mzo.wasl.dto.response.MessageResponse;
import com.mzo.wasl.repository.OfferRepository;
import com.mzo.wasl.repository.TravelerRepository;
import com.mzo.wasl.security.services.UserDetailsImpl;
import com.mzo.wasl.service.OfferService;
import com.mzo.wasl.service.TravelerService;
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
public class OfferController {
    @Autowired
    OfferService offerService;
    @Autowired
    TravelerService travelerService;

    @GetMapping("/offers")
    public ResponseEntity<?> getAllOffers() {
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    @GetMapping("/myoffers")
    @PreAuthorize("hasRole('REGULAR') and @securityService.isTraveler()")
    public ResponseEntity<?> getAllMyOffers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Traveler currentTraveler = travelerService.getTravelerByUserId(userDetails.getId());
        return ResponseEntity.ok(currentTraveler.getOffers());
    }

    @GetMapping("/offers/{id}")
    @PreAuthorize("hasRole('REGULAR')")
    public ResponseEntity<?> getOffer(@PathVariable Long id) {
        return ResponseEntity.ok(offerService.getOffer(id));
    }

    @PostMapping("/offers")
    @PreAuthorize("hasRole('REGULAR') and @securityService.isTraveler()")
    public ResponseEntity<?> addOffer(@Valid @RequestBody OfferRequest offerRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Traveler currentTraveler = travelerService.getTravelerByUserId(userDetails.getId());
        offerService.addOffer(new Offer(offerRequest.getTitle(),
                offerRequest.getDescription(),
                offerRequest.getDepart(),
                offerRequest.getDestination(),
                offerRequest.getDate(),
                offerRequest.getTime(),
                offerRequest.getPrice(),
                offerRequest.getCapacity(),
                offerRequest.getCapacity(),
                offerRequest.getImage(),
                currentTraveler));
        return ResponseEntity.ok(new MessageResponse("Offer added successfully"));
    }

    @PutMapping("/offers/{id}")
    @PreAuthorize("hasRole('REGULAR') and @securityService.isTraveler()")
    public ResponseEntity<?> UpdateOffer(@Valid @RequestBody OfferRequest offerRequest, @PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Traveler currentTraveler = travelerService.getTravelerByUserId(userDetails.getId());
        if (!currentTraveler.getOffers().contains(offerService.getOffer(id).get())) {
            return ResponseEntity.ok(new MessageResponse("This offer does not belong to you"));
        }
        Offer offer = offerService.getOffer(id).get();
        offer.setTitle(offerRequest.getTitle());
        offer.setDescription(offerRequest.getDescription());
        offer.setDepart(offerRequest.getDepart());
        offer.setDestination(offerRequest.getDestination());
        offer.setDate(offerRequest.getDate());
        offer.setTime(offerRequest.getTime());
        offer.setPrice(offerRequest.getPrice());
        offer.setCapacity(offerRequest.getCapacity());
        offer.setImage(offerRequest.getImage());
        offerService.updateOffer(offer);
        return ResponseEntity.ok(new MessageResponse("Offer updated successfully"));
    }

    @DeleteMapping("/offers/{id}")
    @PreAuthorize("hasRole('REGULAR') and @securityService.isTraveler()")
    public ResponseEntity<?> deleteOffer(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Traveler currentTraveler = travelerService.getTravelerByUserId(userDetails.getId());
        if (!currentTraveler.getOffers().contains(offerService.getOffer(id).get())) {
            return ResponseEntity.ok(new MessageResponse("This offer does not belong to you"));
        }
        offerService.deleteOffer(id);
        return ResponseEntity.ok(new MessageResponse("Offer deleted successfully"));
    }
}

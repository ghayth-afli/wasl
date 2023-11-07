package com.mzo.wasl.controllers;

import com.mzo.wasl.models.Offer;
import com.mzo.wasl.models.Traveler;
import com.mzo.wasl.payload.request.OfferRequest;
import com.mzo.wasl.payload.response.MessageResponse;
import com.mzo.wasl.repositories.OfferRepository;
import com.mzo.wasl.repositories.TravelerRepository;
import com.mzo.wasl.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OfferController {
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    TravelerRepository travelerRepository;

    @GetMapping("/offers")
    @PreAuthorize("hasRole('REGULAR')")
    public ResponseEntity<?> getAllOffers() {
        return ResponseEntity.ok(offerRepository.findAll());
    }
    @GetMapping("/myoffers")
    @PreAuthorize("hasRole('REGULAR') and @securityService.isTraveler()")
    public ResponseEntity<?> getAllMyOffers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Traveler currentTraveler = travelerRepository.findByUserId(userDetails.getId());
        return ResponseEntity.ok(currentTraveler.getOffers());
    }

    @GetMapping("/offers/{id}")
    @PreAuthorize("hasRole('REGULAR')")
    public ResponseEntity<?> getOffer(@PathVariable Long id) {
        return ResponseEntity.ok(offerRepository.findById(id));
    }

    @PostMapping("/offers")
    @PreAuthorize("hasRole('REGULAR') and @securityService.isTraveler()")
    public ResponseEntity<?> addOffer(@Valid @RequestBody OfferRequest offerRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Traveler currentTraveler = travelerRepository.findByUserId(userDetails.getId());
        offerRepository.save(new Offer(offerRequest.getTitle(),
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
    public ResponseEntity<?> UpdateOffer(@Valid @RequestBody OfferRequest offerRequest,@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Traveler currentTraveler = travelerRepository.findByUserId(userDetails.getId());
        if (!currentTraveler.getOffers().contains(offerRepository.findById(id).get())){
            return ResponseEntity.ok(new MessageResponse("This offer does not belong to you"));
        }
        Offer offer = offerRepository.findById(id).get();
        offer.setTitle(offerRequest.getTitle());
        offer.setDescription(offerRequest.getDescription());
        offer.setDepart(offerRequest.getDepart());
        offer.setDestination(offerRequest.getDestination());
        offer.setDate(offerRequest.getDate());
        offer.setTime(offerRequest.getTime());
        offer.setPrice(offerRequest.getPrice());
        offer.setCapacity(offerRequest.getCapacity());
        offer.setImage(offerRequest.getImage());
        offerRepository.save(offer);
        return ResponseEntity.ok(new MessageResponse("Offer updated successfully"));
    }

    @DeleteMapping("/offers/{id}")
    @PreAuthorize("hasRole('REGULAR') and @securityService.isTraveler()")
    public ResponseEntity<?> deleteOffer(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Traveler currentTraveler = travelerRepository.findByUserId(userDetails.getId());
        if (!currentTraveler.getOffers().contains(offerRepository.findById(id).get())){
            return ResponseEntity.ok(new MessageResponse("This offer does not belong to you"));
        }
        offerRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Offer deleted successfully"));
    }
}

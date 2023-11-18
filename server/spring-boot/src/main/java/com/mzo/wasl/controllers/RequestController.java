package com.mzo.wasl.controllers;

import com.mzo.wasl.models.*;
import com.mzo.wasl.payload.request.RequestRequest;
import com.mzo.wasl.payload.response.MessageResponse;
import com.mzo.wasl.payload.response.OfferWithRequestsResponse;
import com.mzo.wasl.repositories.OfferRepository;
import com.mzo.wasl.repositories.RequestRepository;
import com.mzo.wasl.repositories.SenderRepository;
import com.mzo.wasl.repositories.TravelerRepository;
import com.mzo.wasl.security.services.UserDetailsImpl;
import com.mzo.wasl.services.CurrentDateAndTime;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class RequestController {
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    SenderRepository senderRepository;
    @Autowired
    CurrentDateAndTime currentDateAndTime;
    @Autowired
    TravelerRepository travelerRepository;
    @PostMapping("/offers/{id}/requests")
    @PreAuthorize("hasRole('REGULAR') and !@securityService.isTraveler()")
    public ResponseEntity<?> submitRequest(@Valid @RequestBody RequestRequest requestRequest, @PathVariable Long id){

        //Check if the offer exists
        if (!offerRepository.existsById(id)){
            return ResponseEntity.ok(new MessageResponse("This Offer does not exist!"));
        }

        //Get the current sender
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Sender currentSender = senderRepository.findByUserId(userDetails.getId());
        Optional<Offer> offer = offerRepository.findById(id);

        //Check if the sender has already submitted a request for this offer
        if (requestRepository.existsByOfferIdAndSenderId(offer.get().getId(),currentSender.getId())){
            return ResponseEntity.ok(new MessageResponse("You have already submitted a request for this offer!"));
        }

        //Check if the remaining capacity is enough
        if (offer.get().getRemainingCapacity()<requestRequest.getWeight()){
            return ResponseEntity.ok(new MessageResponse("The remaining capacity is not enough!"));
        }

        //Save the request
        requestRepository.save(new Request(requestRequest.getDescription(),
                requestRequest.getWeight()*offer.get().getPrice(),
                requestRequest.getWeight(),
                Date.from(currentDateAndTime.getCurrentLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                EStatus.PENDING,
                offer.get(),
                currentSender
                ));

        //Update remaining capacity
        Offer o = new Offer();
        o.setId(offer.get().getId());
        o.setTitle(offer.get().getTitle());
        o.setDescription(offer.get().getDescription());
        o.setDepart(offer.get().getDepart());
        o.setDestination(offer.get().getDestination());
        o.setDate(offer.get().getDate());
        o.setTime(offer.get().getTime());
        o.setPrice(offer.get().getPrice());
        o.setCapacity(offer.get().getCapacity());
        o.setRemainingCapacity(offer.get().getRemainingCapacity()-requestRequest.getWeight());
        o.setImage(offer.get().getImage());
        o.setTraveler(offer.get().getTraveler());
        offerRepository.save(o);

        return ResponseEntity.ok(new MessageResponse("Request submitted successfully!"));
    }

    @GetMapping("/myrequests")
    @PreAuthorize("hasRole('REGULAR') and !@securityService.isTraveler()")
    public ResponseEntity<?> getMyRequests(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Sender currentSender = senderRepository.findByUserId(userDetails.getId());
        return ResponseEntity.ok(requestRepository.findRequestsBySenderId(currentSender.getId()));
    }

    @GetMapping("/myrequests/{id}")
    @PreAuthorize("hasRole('REGULAR') and !@securityService.isTraveler()")
    public ResponseEntity<?> getMyRequest(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Sender currentSender = senderRepository.findByUserId(userDetails.getId());
        Optional<Request> request = requestRepository.findById(id);
        if (!request.isPresent()){
            return ResponseEntity.ok(new MessageResponse("This request does not exist!"));
        }
        if (request.get().getSender().getId()!=currentSender.getId()){
            return ResponseEntity.ok(new MessageResponse("This request does not belong to you!"));
        }
        return ResponseEntity.ok(request);
    }

    @GetMapping("/requests")
    @PreAuthorize("hasRole('REGULAR') and @securityService.isTraveler()")
    public ResponseEntity<?> getMyAllRequests(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Traveler currentTraveler = travelerRepository.findByUserId(userDetails.getId());
        List<Offer> offers = offerRepository.findOffersByTravelerId(currentTraveler.getId());
        List<OfferWithRequestsResponse> offersWithRequests = new ArrayList<>();
        for (Offer offer : offers) {
            List<Request> requests = requestRepository.findRequestsByOfferId(offer.getId());
            OfferWithRequestsResponse offerWithRequestsResponse = new OfferWithRequestsResponse(offer,requests);
            offersWithRequests.add(offerWithRequestsResponse);
        }
        return ResponseEntity.ok(offersWithRequests);
    }

    @GetMapping("/offers/{offer_id}/requests/{request_id}")
    @PreAuthorize("hasRole('REGULAR') and @securityService.isTraveler()")
    public ResponseEntity<?> getRequest(@PathVariable Long offer_id,@PathVariable Long request_id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Traveler currentTraveler = travelerRepository.findByUserId(userDetails.getId());
        List<Offer> offers = offerRepository.findOffersByTravelerId(currentTraveler.getId());
        if (!offers.contains(offerRepository.findById(offer_id).get())){
            return ResponseEntity.ok(new MessageResponse("This offer does not belong to you!"));
        }
        return ResponseEntity.ok(requestRepository.findById(request_id));
    }
}
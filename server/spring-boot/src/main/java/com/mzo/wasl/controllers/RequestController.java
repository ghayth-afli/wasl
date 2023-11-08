package com.mzo.wasl.controllers;

import com.mzo.wasl.models.*;
import com.mzo.wasl.payload.request.RequestRequest;
import com.mzo.wasl.payload.response.MessageResponse;
import com.mzo.wasl.repositories.OfferRepository;
import com.mzo.wasl.repositories.RequestRepository;
import com.mzo.wasl.repositories.SenderRepository;
import com.mzo.wasl.security.services.UserDetailsImpl;
import com.mzo.wasl.services.CurrentDateAndTime;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.Date;
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
}

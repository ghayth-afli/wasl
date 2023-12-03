package com.mzo.wasl.controller;

import com.mzo.wasl.dto.request.RequestRequest;
import com.mzo.wasl.model.Sender;
import com.mzo.wasl.model.Traveler;
import com.mzo.wasl.security.services.UserDetailsImpl;
import com.mzo.wasl.service.OfferService;
import com.mzo.wasl.service.SenderService;
import com.mzo.wasl.service.TravelerService;
import com.mzo.wasl.util.CurrentDateAndTimeUtil;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class PaymentController {
    @Autowired
    OfferService offerService;
    @Autowired
    SenderService senderService;
    @Autowired
    CurrentDateAndTimeUtil currentDateAndTime;
    @Autowired
    TravelerService travelerService;
    String STRIPE_API_KEY = System.getenv().get("STRIPE_API_KEY");
    @PostMapping("offers/{id}/checkout/hosted")
    @PreAuthorize("hasRole('REGULAR') and !@securityService.isTraveler()")
    public ResponseEntity<?> hostedCheckout(@PathVariable Long id, @Valid @RequestBody RequestRequest requestRequest)throws StripeException {
        Stripe.apiKey = STRIPE_API_KEY;
        String clientBaseURL = System.getenv().get("CLIENT_BASE_URL");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Sender currentSender = senderService.getSenderByUserId(userDetails.getId());
        Traveler currentTraveler = travelerService.getTravelerByUserId(userDetails.getId());

        // Check if offer exists
        if (!offerService.getOffer(id).isPresent()) {
            return ResponseEntity.ok("Offer not found");
        }

        /*if (offerService.getOffer(id).get().getTraveler().getId() == currentTraveler.getId()) {
            return ResponseEntity.ok("You can't buy your own offer");
        }*/

        Map<String, String> req = new HashMap<>();
        req.put("description", requestRequest.getDescription());
        req.put("weight", requestRequest.getWeight().toString());
        req.put("offerId", id.toString());
        req.put("senderId", currentSender.getId().toString());

        SessionCreateParams.Builder paramsBuilder =
        SessionCreateParams.builder()
                .setSuccessUrl(clientBaseURL + "/success?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl(clientBaseURL + "/failure")
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .putAllMetadata(req)
                .setCustomerEmail(userDetails.getEmail());

        paramsBuilder.addLineItem(
            SessionCreateParams.LineItem.builder()
                    .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                                    .setCurrency("usd")
                                    .setUnitAmount(Math.round(offerService.getOffer(id).get().getPrice())*100)
                                    .setProductData(
                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                    .setName(offerService.getOffer(id).get().getTitle())
                                                    .setDescription(offerService.getOffer(id).get().getDescription())
                                                    .build()
                                    )
                                    .build()
                    )
                    .setQuantity(Math.round(requestRequest.getWeight()))
        .build());

        Session session = Session.create(paramsBuilder.build());
        return ResponseEntity.ok(session.getUrl());
    }

}

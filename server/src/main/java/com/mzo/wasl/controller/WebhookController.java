package com.mzo.wasl.controller;

import com.google.gson.JsonSyntaxException;
import com.mzo.wasl.model.EStatus;
import com.mzo.wasl.model.Offer;
import com.mzo.wasl.model.Request;
import com.mzo.wasl.model.Sender;
import com.mzo.wasl.service.OfferService;
import com.mzo.wasl.service.RequestService;
import com.mzo.wasl.service.SenderService;
import com.mzo.wasl.util.CurrentDateAndTimeUtil;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class WebhookController {
    @Autowired
    OfferService offerService;
    @Autowired
    SenderService senderService;
    @Autowired
    RequestService requestService;
    @Autowired
    CurrentDateAndTimeUtil currentDateAndTime;
    private final String endpointSecret = "whsec_f61e36c16bb5942825f21121d8fe448e8466f380638f12a05ae33bd4c8e8c38f";

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhookEvent(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) throws JSONException {
        Stripe.apiKey = System.getenv().get("CLIENT_BASE_URL");
        Event event = null;
        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (JsonSyntaxException e) {
            // Handle invalid payload or signature
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (SignatureVerificationException c){
            // Handle signature verification failed
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        // Deserialize the nested object inside the event
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            // Deserialization failed, probably due to an API version mismatch.
            // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
            // instructions on how to handle this case, or return an error here.
        }
        // Handle the event
        switch (event.getType()) {
            case "checkout.session.completed": {
                // Then define and call a function to handle the event checkout.session.completed
                //get metadata
                JSONObject jsonObj = new JSONObject(dataObjectDeserializer.getRawJson());
                String metadata = jsonObj.getString("metadata");
                JSONObject jsonObj2 = new JSONObject(metadata);
                //get description, weight, offerId, senderId
                String description = jsonObj2.getString("description");
                String weight = jsonObj2.getString("weight");
                String offerId = jsonObj2.getString("offerId");
                String senderId = jsonObj2.getString("senderId");
                //get offer and sender
                Optional<Offer> offer = offerService.getOffer(Long.parseLong(offerId));
                Sender sender = senderService.getSender(Long.parseLong(senderId));
                //add request
                requestService.addRequest(new Request(description,
                        offer.get().getPrice()*Double.parseDouble(weight),
                        Double.parseDouble(weight),
                        Date.from(currentDateAndTime.getCurrentLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        EStatus.PENDING,
                        offer.get(),
                        sender
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
                o.setRemainingCapacity(offer.get().getRemainingCapacity()-Double.parseDouble(weight));
                o.setImage(offer.get().getImage());
                o.setTraveler(offer.get().getTraveler());
                offerService.addOffer(o);
                break;
            }
            default:
                System.out.println("Unhandled event type: " + event.getType());
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
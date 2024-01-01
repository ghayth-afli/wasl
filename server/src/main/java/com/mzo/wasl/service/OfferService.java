package com.mzo.wasl.service;

import com.mzo.wasl.model.Offer;

import java.util.List;
import java.util.Optional;

public interface OfferService {
    Boolean existsById(Long id);
    List<Offer> getAllOffers();
    Optional<Offer> getOffer(Long id);
    List<Offer> getOffersByTravelerId(Long travelerId);
    void addOffer(Offer offer);
    void updateOffer(Offer offer);
    void deleteOffer(Long id);
}

package com.mzo.wasl.service;

import com.mzo.wasl.model.Offer;
import com.mzo.wasl.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService{
    @Autowired
    OfferRepository offerRepository;

    @Override
    public Boolean existsById(Long id) {
        return offerRepository.existsById(id);
    }

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public Optional<Offer> getOffer(Long id) {
        return offerRepository.findById(id);
    }

    @Override
    public List<Offer> getOffersByTravelerId(Long travelerId) {
        return offerRepository.findOffersByTravelerId(travelerId);
    }

    @Override
    public void addOffer(Offer offer) {
        offerRepository.save(offer);
    }

    @Override
    public void updateOffer(Offer offer) {
        offerRepository.save(offer);
    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }
}

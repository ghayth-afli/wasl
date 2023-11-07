package com.mzo.wasl.repositories;

import com.mzo.wasl.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {
    List<Offer> findOffersByTravelerId(Long id);
}

package com.mzo.wasl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mzo.wasl.models.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {
    Boolean existsByOfferIdAndSenderId(Long offerId,Long senderId);
}
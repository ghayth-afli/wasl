package com.mzo.wasl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mzo.wasl.models.Request;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {
    Boolean existsByOfferIdAndSenderId(Long offerId,Long senderId);
    List<Request> findRequestsBySenderId(Long id);
    List<Request> findRequestsByOfferId(Long id);
}
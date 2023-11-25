package com.mzo.wasl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mzo.wasl.model.Request;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {
    Boolean existsByOfferIdAndSenderId(Long offerId,Long senderId);
    List<Request> findRequestsBySenderId(Long id);
    List<Request> findRequestsByOfferId(Long id);
}
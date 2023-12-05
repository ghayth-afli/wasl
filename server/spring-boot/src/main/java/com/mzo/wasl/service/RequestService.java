package com.mzo.wasl.service;

import com.mzo.wasl.model.Request;

import java.util.List;
import java.util.Optional;

public interface RequestService {
    Boolean existsByOfferIdAndSenderId(Long offerId, Long senderId);

    void addRequest(Request request);

    Optional<Request> getRequest(Long id);

    List<Request> getRequestsByOfferId(Long offerId);

    void deleteRequest(Long id);

    List<Request> getRequestsBySenderId(Long senderId);
}

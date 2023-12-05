package com.mzo.wasl.service;

import com.mzo.wasl.model.Request;
import com.mzo.wasl.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService{
    @Autowired
    RequestRepository requestRepository;

    @Override
    public Boolean existsByOfferIdAndSenderId(Long offerId, Long senderId) {
        return requestRepository.existsByOfferIdAndSenderId(offerId,senderId);
    }

    @Override
    public void addRequest(Request request) {
        requestRepository.save(request);
    }

    @Override
    public Optional<Request> getRequest(Long id) {
        return requestRepository.findById(id);
    }

    @Override
    public List<Request> getRequestsByOfferId(Long offerId) {
        return requestRepository.findRequestsByOfferId(offerId);
    }

    @Override
    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }

    @Override
    public List<Request> getRequestsBySenderId(Long senderId) {
        return requestRepository.findRequestsBySenderId(senderId);
    }

}

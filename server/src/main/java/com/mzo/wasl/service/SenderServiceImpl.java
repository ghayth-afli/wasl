package com.mzo.wasl.service;

import com.mzo.wasl.model.Sender;
import com.mzo.wasl.repository.SenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SenderServiceImpl implements SenderService{
    @Autowired
    SenderRepository senderRepository;

    @Override
    public Sender getSender(Long id) {
        return senderRepository.findById(id).get();
    }

    @Override
    public Sender getSenderByUserId(Long id) {
        return senderRepository.findByUserId(id);
    }

    @Override
    public Sender addSender(Sender sender) {
        return senderRepository.save(sender);
    }
}

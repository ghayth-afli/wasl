package com.mzo.wasl.service;

import com.mzo.wasl.model.Sender;

public interface SenderService {
    Sender getSender(Long id);
    Sender getSenderByUserId(Long id);
    Sender addSender(Sender sender);
}

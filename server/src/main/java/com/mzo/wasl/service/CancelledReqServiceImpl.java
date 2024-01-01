package com.mzo.wasl.service;

import com.mzo.wasl.model.CancelledReq;
import com.mzo.wasl.repository.CancelledReqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelledReqServiceImpl implements CancelledReqService{
    @Autowired
    private CancelledReqRepository cancelledReqRepository;

    @Override
    public void saveCancelledReq(CancelledReq cancelledReq) {
        cancelledReqRepository.save(cancelledReq);
    }
}

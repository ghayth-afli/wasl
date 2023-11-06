package com.mzo.wasl.payload.request;

import java.util.Date;

import com.mzo.wasl.models.EStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class RequestRequest {
    private String description;
    private Double totalPrice;
    private Double weight;
    private Date startRequest;
    private Date endRequest;
    @Enumerated(EnumType.STRING)
    private EStatus status;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getWeight() {
        return this.weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Date getStartRequest() {
        return this.startRequest;
    }

    public void setStartRequest(Date startRequest) {
        this.startRequest = startRequest;
    }

    public Date getEndRequest() {
        return this.endRequest;
    }

    public void setEndRequest(Date endRequest) {
        this.endRequest = endRequest;
    }

    public EStatus getStatus() {
        return this.status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }
    
}
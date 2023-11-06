package com.mzo.wasl.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double totalPrice;
    private Double weight;
    private Date startRequest;
    private Date endRequest;
    @Enumerated(EnumType.STRING)
    private EStatus status;
    public Request() {}
    public Request(String description, Double totalPrice, Double weight, Date starDate, Date endRequest) {
        this.description=description;
        this.totalPrice=totalPrice;
        this.weight=weight;
        this.startRequest=starDate;
        this.endRequest=endRequest;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
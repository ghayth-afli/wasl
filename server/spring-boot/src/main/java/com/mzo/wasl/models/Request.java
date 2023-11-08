package com.mzo.wasl.models;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(name = "total_price")
    private Double totalPrice;

    private Double weight;

    @Column(name = "start_request")
    private Date startRequest;

    @Column(name = "end_request")
    private Date endRequest;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Sender sender;

    public Request(String description, Double totalPrice, Double weight, Date startRequest,EStatus status, Offer offer,Sender sender) {
        this.description = description;
        this.totalPrice = totalPrice;
        this.weight = weight;
        this.startRequest = startRequest;
        this.status = status;
        this.offer = offer;
        this.sender=sender;
    }
}
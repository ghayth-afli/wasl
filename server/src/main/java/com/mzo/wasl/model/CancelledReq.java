package com.mzo.wasl.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cancelled_requests")
public class CancelledReq {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(name = "total_price")
    private Double totalPrice;

    private Double weight;

    @Column(name = "start_request")
    private Date startRequest;

    @Column(name = "cancelled_date")
    private Date cancelledDate;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Sender sender;

    public CancelledReq(String description, Double totalPrice, Double weight, Date startRequest, Date cancelledDate, Offer offer, Sender sender) {
        this.description = description;
        this.totalPrice = totalPrice;
        this.weight = weight;
        this.startRequest = startRequest;
        this.cancelledDate = cancelledDate;
        this.offer = offer;
        this.sender = sender;
    }
}

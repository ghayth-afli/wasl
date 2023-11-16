package com.mzo.wasl.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String depart;
    private String destination;
    private Date date;
    private Time time;
    private Double price;
    private Double capacity;
    @Column(name = "remaining_capacity")
    private Double remainingCapacity;
    private String image;
    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY,mappedBy = "offer")
    private List<Request> requests;
    @ManyToOne
    @JoinColumn(name = "traveler_id")
    private Traveler traveler;

    public Offer(String title, String description, String depart, String destination, Date date, Time time, Double price, Double capacity, Double remainingCapacity, String image, Traveler traveler) {
        this.title = title;
        this.description = description;
        this.depart = depart;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.price = price;
        this.capacity = capacity;
        this.remainingCapacity = remainingCapacity;
        this.image = image;
        this.traveler = traveler;
    }

    public void setRemainingCapacity(Double remainingCapacity) {
        this.remainingCapacity = remainingCapacity;
    }
}

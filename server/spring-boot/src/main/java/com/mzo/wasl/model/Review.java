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
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private Short rating;
    private Date date;
    @OneToOne
    @JoinColumn(name = "request_id")
    private Request request;

    public Review(String comment, Short rating, Date date, Request request) {
        this.comment = comment;
        this.rating = rating;
        this.date = date;
        this.request = request;
    }
}

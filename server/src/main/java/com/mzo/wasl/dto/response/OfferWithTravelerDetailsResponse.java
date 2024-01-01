package com.mzo.wasl.dto.response;

import com.mzo.wasl.model.Review;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class OfferWithTravelerDetailsResponse {
    private Long id;
    private String title;
    private String description;
    private String depart;
    private String destination;
    private Date date;
    private Time time;
    private Double price;
    private Double capacity;
    private Double remainingCapacity;
    private String image;
    private Long travelerId;
    private String travelerName;
    private String travelerEmail;
    private String travelerPhone;
    private String travelerCountry;
    private String travelerLanguage;
    private String travelerBio;
    private String travelerImage;
    private List<Review> reviews;
}

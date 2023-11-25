package com.mzo.wasl.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.sql.Time;
@Getter
@Setter
public class OfferRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String depart;
    @NotBlank
    private String destination;
    @NotBlank
    private Date date;
    @NotBlank
    private Time time;
    @NotBlank
    private Double price;
    @NotBlank
    private Double capacity;
    @NotBlank
    private String image;
}

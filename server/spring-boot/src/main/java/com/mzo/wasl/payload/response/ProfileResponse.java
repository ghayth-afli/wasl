package com.mzo.wasl.payload.response;

import jakarta.validation.constraints.Size;

public class ProfileResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String bio;
    private String country;
    private String city;
    private String phoneNumber;
    private String language ;
    private String image;

    public ProfileResponse(Integer id, String firstName, String lastName, String bio, String country, String city, String phoneNumber, String language, String image) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.country = country;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.language = language;
        this.image = image;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

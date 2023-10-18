package com.mzo.wasl.payload.request;

public class ProfileRequest {
    private Integer id;
    private String firstName;
    private String lastName;
    private String bio;
    private String country;
    private String city;
    private String phoneNumber;
    private String language ;
    private String image;

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBio() {
        return bio;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLanguage() {
        return language;
    }

    public String getImage() {
        return image;
    }
}

package com.mzo.wasl.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import com.mzo.wasl.model.User;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 20)
    private String firstName;

    @Size(max = 20)
    private String lastName;

    private String bio;

    private String country;

    private String city;

    private String phoneNumber;

    private String language ;

    private String image;

    @Column(length = 20,name = "is_exp",columnDefinition = "boolean default true")
    private boolean isExp;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Profile(String firstName, String lastName, String bio, String country, String city, String phoneNumber, String language, String image, boolean isExp, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.country = country;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.language = language;
        this.image = image;
        this.isExp = isExp;
        this.user = user;
    }

    public Profile() {
    }

    public Profile(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isExp() {
        return isExp;
    }

    public void setExp(boolean exp) {
        isExp = exp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

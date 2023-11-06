package com.mzo.wasl.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

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

    private String PhoneNumber;

    private String language ;

<<<<<<< HEAD:server/spring-boot/src/main/java/com/mzo/wasl/models/Profile.java
    private String image;

<<<<<<< HEAD:server/spring-boot/src/main/java/com/mzo/wasl/model/Profile.java
=======
>>>>>>> parent of e3f7f19 (Merge branch 'main' of https://github.com/belhajManel/wasl):server/spring-boot/src/main/java/com/mzo/wasl/model/Profile.java
    @Column(length = 20,name = "is_exp",columnDefinition = "boolean default true")
    private boolean isExp;
=======
    @Column(length = 20,name = "is_traveler",columnDefinition = "boolean default false")
    private boolean isTraveler;
>>>>>>> parent of b170955 (Revert "Added switch functionality"):server/spring-boot/src/main/java/com/mzo/wasl/models/Profile.java

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

<<<<<<< HEAD:server/spring-boot/src/main/java/com/mzo/wasl/model/Profile.java
<<<<<<< HEAD:server/spring-boot/src/main/java/com/mzo/wasl/models/Profile.java
    public Profile(String firstName, String lastName, String bio, String country, String city, String phoneNumber, String language, String image, boolean isExp, User user) {
=======

    public Profile(String firstName, String lastName, String bio, String country, String city, String phoneNumber, String language) {
>>>>>>> parent of e3f7f19 (Merge branch 'main' of https://github.com/belhajManel/wasl):server/spring-boot/src/main/java/com/mzo/wasl/model/Profile.java
=======
    public Profile(String firstName, String lastName, String bio, String country, String city, String phoneNumber, String language, String image, boolean isTraveler, User user) {
>>>>>>> parent of b170955 (Revert "Added switch functionality"):server/spring-boot/src/main/java/com/mzo/wasl/models/Profile.java
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.country = country;
        this.city = city;
        PhoneNumber = phoneNumber;
        this.language = language;
<<<<<<< HEAD:server/spring-boot/src/main/java/com/mzo/wasl/models/Profile.java
        this.image = image;
        this.isTraveler = isTraveler;
        this.user = user;
=======
>>>>>>> parent of e3f7f19 (Merge branch 'main' of https://github.com/belhajManel/wasl):server/spring-boot/src/main/java/com/mzo/wasl/model/Profile.java
    }

    public Profile() {
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
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isExp() {
        return isExp;
    }

<<<<<<< HEAD:server/spring-boot/src/main/java/com/mzo/wasl/models/Profile.java
    public void setImage(String image) {
        this.image = image;
    }

    public boolean isTraveler() {
        return this.isTraveler;
    }

<<<<<<< HEAD:server/spring-boot/src/main/java/com/mzo/wasl/model/Profile.java
=======
>>>>>>> parent of e3f7f19 (Merge branch 'main' of https://github.com/belhajManel/wasl):server/spring-boot/src/main/java/com/mzo/wasl/model/Profile.java
    public void setExp(boolean exp) {
        isExp = exp;
=======
    public void setIsTraveler(boolean isTraveler) {
        this.isTraveler = isTraveler;
>>>>>>> parent of b170955 (Revert "Added switch functionality"):server/spring-boot/src/main/java/com/mzo/wasl/models/Profile.java
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

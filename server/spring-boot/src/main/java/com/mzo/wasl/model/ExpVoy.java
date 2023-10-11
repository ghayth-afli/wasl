package com.mzo.wasl.model;

import jakarta.persistence.*;

@Entity
@Table(name = "expvoy")
public class ExpVoy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20,name = "is_exp",columnDefinition = "boolean default true")
    private boolean isExp;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public ExpVoy(User user, Profile profile) {
        this.user = user;
        this.profile = profile;
    }

    public ExpVoy() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}

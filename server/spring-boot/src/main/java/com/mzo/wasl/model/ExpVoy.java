package com.mzo.wasl.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpVoy {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    @Getter
    @ManyToOne
    @JoinColumn(name = "id")
    private Profile profile;

    @Column(name="isExp")
    private boolean isExp;

    public void setIdExpVoy(int idExpVoy) {
        this.id = idExpVoy;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public boolean isExp() {
        return isExp;
    }

    public void setExp(boolean exp) {
        isExp = exp;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Profile getProfile() {
        return profile;
    }
}

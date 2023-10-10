package com.mzo.wasl.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expvoy")
public class ExpVoy {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;


    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Getter
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name="isExp")
    private boolean isExp;

    public void setId(int id) {
        this.id = id;
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
}

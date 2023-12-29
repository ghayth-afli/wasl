package com.mzo.wasl.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "supports")
public class Support {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(fetch=FetchType.LAZY,mappedBy = "support")
    private List<Ticket> tickets;

    public Support(User user) {
        this.user = user;
    }
}

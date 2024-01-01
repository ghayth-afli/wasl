package com.mzo.wasl.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String objet;
    private String description;
    private ETicketStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "support_id")
    private Support support;

    public Ticket(String objet, String description, ETicketStatus status, User user) {
        this.objet = objet;
        this.description = description;
        this.status = status;
        this.user = user;
    }
}

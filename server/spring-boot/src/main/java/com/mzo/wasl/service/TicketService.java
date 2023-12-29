package com.mzo.wasl.service;

import com.mzo.wasl.model.Ticket;
import com.mzo.wasl.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<Ticket> getAllTickets();
    Ticket createTicket(Ticket ticket);
    Optional<Ticket> getTicketById(Long id);
    Optional<Ticket> getTicketByUserId(Long userId);
}

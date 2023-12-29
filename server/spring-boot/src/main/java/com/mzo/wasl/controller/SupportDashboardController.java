package com.mzo.wasl.controller;

import com.mzo.wasl.model.ETicketStatus;
import com.mzo.wasl.model.Support;
import com.mzo.wasl.security.services.UserDetailsImpl;
import com.mzo.wasl.service.SupportService;
import com.mzo.wasl.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class SupportDashboardController {
    @Autowired
    TicketService ticketService;
    @Autowired
    SupportService supportService;
    @GetMapping("/support/dashboard")
    @PreAuthorize("hasRole('SUPPORT')")
    public ResponseEntity<?> getSupportDashboard() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Support currentSupport = supportService.getSupportByUserId(userDetails.getId()).get();
        //get number of tickets assigned to support
        Integer NumberOfChoosenTickets = ticketService.getTicketsBySupportId(currentSupport.getId()).size();
        //get number of tickets
        Integer NumberOfTickets = ticketService.getAllTickets().size();
        //get number of resolved tickets choosen by support
        Integer NumberOfResolvedTickets = ticketService.getTicketsBySupportId(currentSupport.getId()).stream().filter(ticket -> ticket.getStatus()== ETicketStatus.RESOLVED).toList().size();
        //get number of In Progress tickets choosen by support
        Integer NumberOfInProgressTickets = ticketService.getTicketsBySupportId(currentSupport.getId()).stream().filter(ticket -> ticket.getStatus()== ETicketStatus.IN_PROGRESS).toList().size();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Number Of Choosen Tickets", NumberOfChoosenTickets);
        map.put("Number Of Tickets", NumberOfTickets);
        map.put("Number Of Resolved Tickets", NumberOfResolvedTickets);
        map.put("Number Of In Progress Tickets", NumberOfInProgressTickets);
        return ResponseEntity.ok(map);
    }
}

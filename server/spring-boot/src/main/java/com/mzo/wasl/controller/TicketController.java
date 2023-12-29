package com.mzo.wasl.controller;

import com.mzo.wasl.dto.request.TicketRequest;
import com.mzo.wasl.dto.response.MessageResponse;
import com.mzo.wasl.model.ETicketStatus;
import com.mzo.wasl.model.Support;
import com.mzo.wasl.model.Ticket;
import com.mzo.wasl.model.User;
import com.mzo.wasl.security.services.UserDetailsImpl;
import com.mzo.wasl.service.SupportService;
import com.mzo.wasl.service.TicketService;
import com.mzo.wasl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @Autowired
    UserService userService;
    @Autowired
    SupportService supportService;

    //get all tickets for admin and support
    @GetMapping("/tickets")
    @PreAuthorize("hasRole('SUPPORT') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllTickets(){
        return ResponseEntity.ok(ticketService.getAllTickets());
    }
    @GetMapping("/mytickets")
    @PreAuthorize("hasRole('REGULAR')")
    public ResponseEntity<?> getAllMyTickets(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User currentUser = userService.getUserById(userDetails.getId()).get();
        return ResponseEntity.ok(ticketService.getTicketsByUserId(currentUser.getId()));
    }

    //get ticket by id for regular user
    @GetMapping("/mytickets/{id}")
    @PreAuthorize("hasRole('REGULAR')")
    public ResponseEntity<?> getMyTicketById(@PathVariable("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User currentUser = userService.getUserById(userDetails.getId()).get();
        List<Ticket> tickets = ticketService.getTicketsByUserId(currentUser.getId());
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        if(!tickets.contains(ticket.get())){
            return ResponseEntity.badRequest().body("Ticket not found");
        }
        return ResponseEntity.ok(ticket);
    }

    //get all tickets for support
    @GetMapping("/allmychosentickets")
    @PreAuthorize("hasRole('SUPPORT')")
    public ResponseEntity<?> getAllMyChosenTickets(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Support currentSupport = supportService.getSupportByUserId(userDetails.getId()).get();
        return ResponseEntity.ok(ticketService.getTicketsBySupportId(currentSupport.getId()));
    }

    //get ticket by id for support
    @GetMapping("/allmychosentickets/{id}")
    @PreAuthorize("hasRole('SUPPORT')")
    public ResponseEntity<?> getMyChosenTicketById(@PathVariable("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Support currentSupport = supportService.getSupportByUserId(userDetails.getId()).get();
        List<Ticket> tickets = ticketService.getTicketsBySupportId(currentSupport.getId());
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        if(!tickets.contains(ticket.get())){
            return ResponseEntity.badRequest().body("Ticket not found");
        }
        return ResponseEntity.ok(ticket);
    }

    //create ticket for regular user
    @PostMapping("/tickets")
    @PreAuthorize("hasRole('REGULAR')")
    public ResponseEntity<?> createTicket(@RequestBody TicketRequest ticketRequest){
        //get current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User currentUser = userService.getUserById(userDetails.getId()).get();
        Ticket ticket = new Ticket(ticketRequest.getObjet(),
                ticketRequest.getDescription(),
                ETicketStatus.PENDING, currentUser);
        ticketService.createTicket(ticket);
        return ResponseEntity.ok(new MessageResponse("Ticket created successfully"));
    }

    //choose ticket for support
    @PutMapping("/tickets/{id}/choose")
    @PreAuthorize("hasRole('SUPPORT')")
    public ResponseEntity<?> ChooseTicket(@PathVariable("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Support currentSupport = supportService.getSupportByUserId(userDetails.getId()).get();
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        if (!ticket.isPresent()){
            return ResponseEntity.badRequest().body("Ticket not found");
        }
        ticket.get().setSupport(currentSupport);
        ticket.get().setStatus(ETicketStatus.IN_PROGRESS);
        ticketService.createTicket(ticket.get());
        return ResponseEntity.ok("Ticket chosen successfully");
    }

    //close ticket for support
    @PutMapping("/tickets/{id}/close")
    @PreAuthorize("hasRole('SUPPORT')")
    public ResponseEntity<?> CloseTicket(@PathVariable("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Support currentSupport = supportService.getSupportByUserId(userDetails.getId()).get();
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        if (!ticket.isPresent()){
            return ResponseEntity.badRequest().body("Ticket not found");
        }
        ticket.get().setStatus(ETicketStatus.RESOLVED);
        ticketService.createTicket(ticket.get());
        return ResponseEntity.ok("Ticket closed successfully");
    }
}
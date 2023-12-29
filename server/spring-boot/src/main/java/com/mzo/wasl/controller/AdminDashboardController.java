package com.mzo.wasl.controller;

import com.mzo.wasl.service.OfferService;
import com.mzo.wasl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class AdminDashboardController {
    @Autowired
    OfferService offerService;
    @Autowired
    UserService userService;

    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAdminDashboard() {
        //get number of offers having date greater than current date
        Integer NumberOfclosedGigs = offerService.getAllOffers().stream().filter(offer -> offer.getDate().after(new Date())).toList().size();
        //get number of offers having date less than current date
        Integer NumberOfOpenedGigs = offerService.getAllOffers().stream().filter(offer -> offer.getDate().before(new Date())).toList().size();
        //get number of offers
        Integer NumberOfOffers = offerService.getAllOffers().size();
        //get number of Regular Users
        Integer NumberOfRegularUsers = userService.getAllRegulars().size();
        //get number of client supports
        Integer NumberOfClientSupports = userService.getAllSupports().size();
        //create a map to store the data
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Number Of closed Gigs", NumberOfclosedGigs);
        map.put("Number Of Opened Gigs", NumberOfOpenedGigs);
        map.put("Number Of Offers", NumberOfOffers);
        map.put("Number Of Regular Users", NumberOfRegularUsers);
        map.put("Number Of Client Supports", NumberOfClientSupports);
        return ResponseEntity.ok(map);
    }
}

package com.mzo.wasl.controller;

import com.mzo.wasl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class RegularController {
    @Autowired
    UserService userService;

    @GetMapping("/regulars")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllRegulars() {
        return ResponseEntity.ok(userService.getAllRegulars());
    }
}

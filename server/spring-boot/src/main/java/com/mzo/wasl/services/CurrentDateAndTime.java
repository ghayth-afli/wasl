package com.mzo.wasl.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
@Service("CurrentDateAndTime")
public class CurrentDateAndTime {
    public LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    public LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    public LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }

    public ZonedDateTime getCurrentZonedDateTime() {
        return ZonedDateTime.now();
    }
}

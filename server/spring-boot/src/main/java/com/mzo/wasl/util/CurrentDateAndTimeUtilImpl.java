package com.mzo.wasl.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

@Component
public class CurrentDateAndTimeUtilImpl implements CurrentDateAndTimeUtil{
    @Override
    public LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    @Override
    public LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    @Override
    public LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }

    @Override
    public ZonedDateTime getCurrentZonedDateTime() {
        return ZonedDateTime.now();
    }
}

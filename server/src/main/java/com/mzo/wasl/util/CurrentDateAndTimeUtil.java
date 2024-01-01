package com.mzo.wasl.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

public interface CurrentDateAndTimeUtil {
    LocalDate getCurrentLocalDate();

    LocalDateTime getCurrentLocalDateTime();

    LocalTime getCurrentLocalTime();

    ZonedDateTime getCurrentZonedDateTime();
}

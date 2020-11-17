package com.maerks.service;

import com.maerks.dto.request.BookingDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.AbstractMap;

public interface BookingService {
    AbstractMap.SimpleEntry<String, Integer> checkAvailable(@RequestBody BookingDto bookingDto);

    AbstractMap.SimpleEntry<String, Integer> save(BookingDto bookingDto);
}

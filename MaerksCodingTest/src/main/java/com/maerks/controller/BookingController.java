package com.maerks.controller;

import com.maerks.dto.request.BookingDto;
import com.maerks.service.impl.BookingServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.AbstractMap;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    BookingServiceImpl bookingService;

    @ApiOperation(value = "checkAvailable")
    @PostMapping(value = "/checkAvailable", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AbstractMap.SimpleEntry<String, Integer> checkAvailable(@RequestBody @Valid BookingDto bookingDto) {
        return bookingService.checkAvailable(bookingDto);
    }

    @ApiOperation(value = "save")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AbstractMap.SimpleEntry<String, Integer> save(@RequestBody @Valid BookingDto bookingDto) {
        return bookingService.save(bookingDto);
    }
}
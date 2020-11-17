package com.maerks.MaerksCodingTest.impl;

import com.maerks.BookingRepository;
import com.maerks.dto.request.BookingDto;
import com.maerks.dto.response.AvailabilityResponse;
import com.maerks.entities.Bookings;
import com.maerks.enums.ContainerType;
import com.maerks.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Book;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BookingServiceTest {
    @Mock
    RestTemplate restTemplate;
    @Mock
    BookingRepository bookingRepository;
    @InjectMocks
    BookingServiceImpl bookingService;

    @AfterEach
    void tearDown() {
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void savePositiveFlow() {
        when(bookingRepository.findMax()).thenReturn(957000005);
        Bookings bookings = new Bookings();
        bookings.setId(957000006);
        when(bookingRepository.save(any(Bookings.class))).thenReturn(bookings);
        AbstractMap.SimpleEntry<String, Integer> savedBooking = bookingService.save(new BookingDto());
        assertEquals(957000006, savedBooking.getValue());
    }

    @Test
    void checkAvailablePositiveFlowExpectValue0() {
        when(bookingRepository.findByContainerSizeAndContainerTypeAndOriginAndDestinationAndQuantity(
                any(Integer.class),
                any(ContainerType.class),
                any(String.class),
                any(String.class),
                any(Integer.class)
        )).thenReturn(new ArrayList<>());
        BookingDto bookingDto = new BookingDto();
        bookingDto.setContainerType(ContainerType.DRY);
        bookingDto.setContainerSize("20");
        bookingDto.setOrigin("Southampton");
        bookingDto.setDestination("Singapore");
        bookingDto.setQuantity(5);

        AbstractMap.SimpleEntry<String, Integer> response = bookingService.checkAvailable(bookingDto);
        assertEquals(0, (int) response.getValue());
    }

    @Test
    void checkAvailablePositiveFlowExpectValue2() {
        ArrayList<Bookings> bookings = new ArrayList<>();
        bookings.add(new Bookings());
        bookings.add(new Bookings());
        when(bookingRepository.findByContainerSizeAndContainerTypeAndOriginAndDestinationAndQuantity(
                any(Integer.class),
                any(ContainerType.class),
                any(String.class),
                any(String.class),
                any(Integer.class)
        )).thenReturn(bookings);
        BookingDto bookingDto = new BookingDto();
        bookingDto.setContainerType(ContainerType.DRY);
        bookingDto.setContainerSize("20");
        bookingDto.setOrigin("Southampton");
        bookingDto.setDestination("Singapore");
        bookingDto.setQuantity(5);
        AbstractMap.SimpleEntry<String, Integer> response = bookingService.checkAvailable(bookingDto);
        assertEquals(2, (int) response.getValue());
    }
}
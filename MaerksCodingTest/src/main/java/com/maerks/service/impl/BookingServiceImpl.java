package com.maerks.service.impl;

import com.maerks.BookingRepository;
import com.maerks.dto.request.BookingDto;
import com.maerks.entities.Bookings;
import com.maerks.service.BookingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    BookingRepository bookingRepository;

    @Override
    public AbstractMap.SimpleEntry<String, Integer> checkAvailable(@RequestBody BookingDto bookingDto) {
        List<Bookings> bookingsList = bookingRepository.findByContainerSizeAndContainerTypeAndOriginAndDestinationAndQuantity(
                Integer.parseInt(bookingDto.getContainerSize()),
                bookingDto.getContainerType(),
                bookingDto.getOrigin(),
                bookingDto.getDestination(),
                bookingDto.getQuantity()
        );
        return new AbstractMap.SimpleEntry<>("availableSpace", bookingsList.size());
    }

    @Override
    public AbstractMap.SimpleEntry<String, Integer> save(BookingDto bookingDto) {
        //call the cassandra DB and save the BookingDto Information
        Bookings bookings = new Bookings();
        BeanUtils.copyProperties(bookingDto, bookings);
        bookings.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        //find max id and do +1 and then save the same.
        Integer max = bookingRepository.findMax();
        if (max == null) {
            max = 957000001;
        }
        bookings.setId(max + 1);
        Bookings savedBookings = bookingRepository.save(bookings);
        return new AbstractMap.SimpleEntry<>("bookingRef", savedBookings.getId());
    }
}
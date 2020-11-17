package com.maerks.service.impl;

import com.maerks.dto.response.AvailabilityResponse;
import com.maerks.service.ProxyService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.AbstractMap;

@Service
public class ProxyServiceImpl implements ProxyService {
    @Autowired
    RestTemplate restTemplate;

    @Override
    public AbstractMap.SimpleEntry<String, Boolean> checkAvailable(@RequestBody String containerDto, HttpHeaders headers) {
        //call an external service at endpoint: https://maersk.com/api/bookings/checkAvailable
        ResponseEntity<AvailabilityResponse> availabilityResponse = restTemplate.exchange("http://localhost:8081/maerks/api/bookings/checkAvailable", HttpMethod.POST, new HttpEntity<>(containerDto, headers), AvailabilityResponse.class);
        if (ObjectUtils.allNotNull(availabilityResponse, availabilityResponse.getBody(), availabilityResponse.getBody().getAvailableSpace()) && availabilityResponse.getBody().getAvailableSpace() > 0)
            return new AbstractMap.SimpleEntry<>("available", true);
        else
            return new AbstractMap.SimpleEntry<>("available", false);
    }

    @Override
    public ResponseEntity<String> save(String body, HttpHeaders headers) {
        return restTemplate.exchange("http://localhost:8081/maerks/api/bookings", HttpMethod.POST, new HttpEntity<>(body, headers), String.class);
    }
}
package com.maerks.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.AbstractMap;

public interface ProxyService {
    AbstractMap.SimpleEntry<String, Boolean> checkAvailable(@RequestBody String bookingDto, HttpHeaders headers);

    ResponseEntity<String> save(String body, HttpHeaders headers);
}

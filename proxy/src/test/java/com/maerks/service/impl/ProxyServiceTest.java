package com.maerks.service.impl;

import com.maerks.dto.response.AvailabilityResponse;
import com.maerks.util.Helper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.AbstractMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProxyServiceTest {
    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    ProxyServiceImpl proxyService;

    @AfterEach
    void tearDown() {
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void savePositiveFlow() {
        ResponseEntity<String> responseReturned = ResponseEntity.ok(Helper.SAVE_RESPONSE);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(String.class))).thenReturn(responseReturned);
        ResponseEntity<String> responseEntity = proxyService.save(Helper.CONTAINER_INFO, new HttpHeaders());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(Objects.requireNonNull(responseEntity.getBody()).contains("bookingRef"));
    }

    @Test
    void checkAvailablePositiveFlowExpectValueTrue() {
        ResponseEntity<AvailabilityResponse> responseReturned = ResponseEntity.ok(new AvailabilityResponse(1));
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(AvailabilityResponse.class))).thenReturn(responseReturned);
        AbstractMap.SimpleEntry<String, Boolean> response = proxyService.checkAvailable(Helper.SAVE_RESPONSE, new HttpHeaders());
        assertTrue(response.getValue());
    }

    @Test
    void checkAvailablePositiveFlowExpectValueFalse() {
        ResponseEntity<AvailabilityResponse> responseReturned = ResponseEntity.ok(new AvailabilityResponse(0));
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(AvailabilityResponse.class))).thenReturn(responseReturned);
        AbstractMap.SimpleEntry<String, Boolean> response = proxyService.checkAvailable(Helper.SAVE_RESPONSE, new HttpHeaders());
        assertFalse(response.getValue());
    }
}
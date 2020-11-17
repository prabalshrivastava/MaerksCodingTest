package com.maerks.controller;

import com.maerks.service.ProxyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/bookings")
public class ProxyController {
    @Autowired
    ProxyService proxyService;

    @ApiOperation(value = "checkAvailable")
    @PostMapping(value = "/checkAvailable", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AbstractMap.SimpleEntry<String, Boolean> checkAvailable(@RequestBody String body, @RequestHeader HttpHeaders headers) {
        return proxyService.checkAvailable(body,headers);
    }

    @ApiOperation(value = "save")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody String body, @RequestHeader HttpHeaders headers) {
        return proxyService.save(body,headers);
    }
}
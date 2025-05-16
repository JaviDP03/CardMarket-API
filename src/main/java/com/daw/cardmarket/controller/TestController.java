package com.daw.cardmarket.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class TestController {

    @Value("${build.time}")
    private String buildTime;

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("<p>RUNNING</p><p>build time: " + buildTime + "</p>");
    }
}

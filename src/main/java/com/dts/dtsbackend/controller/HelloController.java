package com.dts.dtsbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from DTS Backend!";
    }
    
    @GetMapping("/health")
    public String health() {
        return "Backend is running!";
    }
}

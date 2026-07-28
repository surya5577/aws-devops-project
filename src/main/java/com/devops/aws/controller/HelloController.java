package com.devops.aws.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Welcome to Enterprise DevOps Project - Version 2!";
    }

    @GetMapping("/health")
    public String health() {
        return "Application is Running Successfully!";
    }

    @GetMapping("/version")
    public String version() {
        return "Version 1.0.0";
    }
}

package com.emi.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class rest {

    @GetMapping("/")
    public String home() {
        return "Running...";
    }
}

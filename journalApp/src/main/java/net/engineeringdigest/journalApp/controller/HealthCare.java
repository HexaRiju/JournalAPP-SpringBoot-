package net.engineeringdigest.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCare {

    @GetMapping("/health-check")
    public String healthCare() {
        return "ok";
    }
}

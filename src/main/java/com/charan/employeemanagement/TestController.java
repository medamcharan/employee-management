package com.charan.employeemanagement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TestController {
    @GetMapping("/")
    public String hello() {
        return "App is running!";
    }
}

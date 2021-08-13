package com.example.SpringBootJWTTutorial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping({"/hello"})
    public String firstPage() {
        return "Hello World !";
    }

}

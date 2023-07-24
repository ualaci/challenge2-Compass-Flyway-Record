package com.challenge2.challenge2.restControllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("/studentHello")
    public String studentHello() {
        return  "Hello Student";
    }
}

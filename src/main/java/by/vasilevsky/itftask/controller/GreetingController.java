package by.vasilevsky.itftask.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class GreetingController {
    private static final String GREETING_MSG = "Hello Admin!!!";

    @GetMapping
    public String greet() {

           return GREETING_MSG;
       }
}

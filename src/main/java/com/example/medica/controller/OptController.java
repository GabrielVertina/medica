package com.example.medica.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OptController {
    
@GetMapping("/hello")

public String hello(){
    System.out.println("TestController.hello()");
    return "hi";

}



}

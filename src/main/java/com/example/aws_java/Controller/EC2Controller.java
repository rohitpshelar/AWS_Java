package com.example.aws_java.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EC2Controller {

    @GetMapping
    public String hello(){
        return "Hello to AWS EC2 by Rohit - AWS profile";
    }
}

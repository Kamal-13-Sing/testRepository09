package com.SpringSecurity01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @GetMapping("/admin")
    public String admin(){

        return "Admin Api Details for View, Edit, Delete";
    }

    @GetMapping("/customer")
    public String customer(){

        return "Customer Api Details for View";
    }


}

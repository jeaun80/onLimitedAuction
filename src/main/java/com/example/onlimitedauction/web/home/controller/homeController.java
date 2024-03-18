package com.example.onlimitedauction.web.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class homeController {


    @GetMapping("/")
    public String home(Model m) {
        m.addAttribute("data","hello");
        return "index";
    }
    String s;
}

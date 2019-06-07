package com.example.enlistenglish.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class indexController {

    @RequestMapping(value = {"/test"})
    public String showindex(){
        return "login";
    }
}

package com.rest.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class ViewController {

    @GetMapping("/index") //세부적인 url mapping
    public String indexpage(Model model, HttpSession session) {
        return "index";
    }
    @GetMapping("/login")
    public String loginpage(Model model, HttpSession session) {
        return "login";
    }
    @GetMapping("/register")
    public String registerpage(Model model, HttpSession session) {
        return "register";
    }
}

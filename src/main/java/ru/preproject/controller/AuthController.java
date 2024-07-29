package ru.preproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/")
    public String redirectToLogInPage() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String openLogInPage() {
        return "auth/signin";
    }


    @GetMapping("/signup")
    public String openRegistration() {
        return "auth/signup";
    }
}
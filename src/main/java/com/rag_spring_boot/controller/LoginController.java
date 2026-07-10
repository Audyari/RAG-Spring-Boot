package com.rag_spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // ← BUKAN @RestController!
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";  // -> templates/login.html
    }
}

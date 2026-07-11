package com.rag_spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "RAG Spring Boot");
        model.addAttribute("version", "1.0.0");
        return "dashboard"; 
    }
}

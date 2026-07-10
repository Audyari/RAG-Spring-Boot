package com.rag_spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafTestController {

    @GetMapping("/test-thymeleaf")
    public String testThymeleaf(Model model) {
        model.addAttribute("message", "Halo! Thymeleaf BERHASIL JALAN! 🚀");
        return "test";
    }
}

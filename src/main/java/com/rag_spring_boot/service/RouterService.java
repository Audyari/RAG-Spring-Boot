package com.rag_spring_boot.service;

import org.springframework.stereotype.Service;

@Service
public class RouterService {
   public String route(String query) {
        if (query.matches(".*\\d+.*")) {
            return "calculator";
        } else if (query.toLowerCase().contains("rag")) {
            return "rag";
        } else if (query.toLowerCase().contains("hai") || query.toLowerCase().contains("halo")) {
            return "greeting";
        } else {
            return "general";
        }    
   }
}

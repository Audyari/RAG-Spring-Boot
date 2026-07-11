package com.rag_spring_boot.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class SafetyService {

    // Daftar kata berbahaya
    private static final List<String> BLACKLIST = Arrays.asList(
        "hack", "crack", "exploit", "malware", "virus",
        "inject", "bypass", "delete database", "drop table",
        "shutdown", "format", "rm -rf", "sudo", "chmod"
    );

    // Cek apakah query aman
    public boolean isSafe(String query) {
        if (query == null || query.isEmpty()) {
            return false;
        }
        String q = query.toLowerCase();
        for (String word : BLACKLIST) {
            if (q.contains(word)) {
                return false;
            }
        }
        return true;
    }

    // Bersihkan query dari karakter berbahaya
    public String sanitize(String query) {
        if (query == null) return "";
        return query.replaceAll("[<>\"'`;]", "");
    }

    // Pesan error keamanan
    public String getSafetyMessage() {
        return "⚠️ Query mengandung kata atau karakter berbahaya!";
    }

    // Cek apakah ada karakter berbahaya
    public boolean hasDangerousCharacters(String query) {
        if (query == null) return false;
        return query.matches(".*[<>\"'`;].*");
    }
}

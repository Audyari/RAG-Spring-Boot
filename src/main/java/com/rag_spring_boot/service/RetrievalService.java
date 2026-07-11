package com.rag_spring_boot.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RetrievalService {

    // Mencari chunk yang mengandung kata kunci
    public List<Map<String, Object>> search(VectorStoreService store, String query) {
        List<Map<String, Object>> results = new ArrayList<>();
        String q = query.toLowerCase().trim();
        
        // Kalau query kosong, return kosong
        if (q.isEmpty()) {
            return results;
        }

        // Cari di semua data yang tersimpan
        for (var entry : store.findAll().entrySet()) {
            Long id = entry.getKey();
            Map<String, Object> data = entry.getValue();
            String text = data.get("text").toString().toLowerCase();
            
            // Kalau mengandung kata kunci, tambahkan ke hasil
            if (text.contains(q)) {
                Map<String, Object> result = new HashMap<>();
                result.put("id", id);
                result.put("text", data.get("text"));
                result.put("metadata", data.get("metadata"));
                result.put("score", 1.0); // Dummy score
                results.add(result);
            }
        }
        
        return results;
    }

    // Mencari dengan multiple keywords (split by spasi)
    public List<Map<String, Object>> searchMultiKeyword(VectorStoreService store, String query) {
        List<Map<String, Object>> results = new ArrayList<>();
        String[] keywords = query.toLowerCase().trim().split("\\s+");
        
        for (var entry : store.findAll().entrySet()) {
            Long id = entry.getKey();
            Map<String, Object> data = entry.getValue();
            String text = data.get("text").toString().toLowerCase();
            
            int matchCount = 0;
            for (String keyword : keywords) {
                if (text.contains(keyword)) {
                    matchCount++;
                }
            }
            
            if (matchCount > 0) {
                Map<String, Object> result = new HashMap<>();
                result.put("id", id);
                result.put("text", data.get("text"));
                result.put("metadata", data.get("metadata"));
                result.put("match_count", matchCount);
                result.put("score", (double) matchCount / keywords.length);
                results.add(result);
            }
        }
        
        // Urutkan berdasarkan score tertinggi
        results.sort((a, b) -> Double.compare(
            (Double) b.get("score"), 
            (Double) a.get("score")
        ));
        
        return results;
    }
}

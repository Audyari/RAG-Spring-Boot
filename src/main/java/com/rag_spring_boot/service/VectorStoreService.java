package com.rag_spring_boot.service;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class VectorStoreService {

    private final Map<Long, Map<String, Object>> store = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Long save(String text, Map<String, Object> metadata){
        Long id = idGenerator.getAndIncrement();
        Map<String, Object> entry = new HashMap<>();
        entry.put("text",text);
        entry.put("metadata",metadata);
        entry.put("vector", generateDummyVector(text));
        store.put(id, entry);
        System.out.println("✅ Tersimpan ID: " + id + " | Text: " + text);   
        return id;
    }

    private List<Double> generateDummyVector(String text)   {
        List<Double> vector = new ArrayList<>();
        for (char c : text.toCharArray()){
            vector.add((double)c);
        }    
        return vector;
    }

    public Map<Long, Map <String,Object>> findAll(){
        return store;
    }

    public Map<String, Object> findById(Long id) {
        return store.get(id);
    }

    public int count() {
        return store.size();

    }

    public void deleteAll(){
        store.clear();
        idGenerator.set(1);
        System.out.println("🗑️ Semua data dihapus!");  
    }
}

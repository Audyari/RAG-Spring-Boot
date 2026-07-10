package com.rag_spring_boot.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class MetadataService {

    public Map<String, Object> createMetadata(String chunk, String source) {

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("source", source);
        metadata.put("length", chunk.length());
        metadata.put("wordCount",chunk.split(" ").length);
        return metadata;
    
    }
}




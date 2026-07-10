package com.rag_spring_boot.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ChunkingService {
    public List<String> chunkText(String text, int chunksize ){
       
        text = text.replaceAll("\\r\\n|\\r|\\n", " ")  
                   .replaceAll("\\s+", " ")            
                   .trim();

        List<String> chunks = new ArrayList<>();
        String[] words = text.split(" ");
        
        for (int i = 0; i < words.length; i += chunksize){
            chunks.add(String.join(" ",Arrays.copyOfRange(words, i ,Math.min(i + chunksize, words.length ))));    
        }

        return chunks;

    }
}

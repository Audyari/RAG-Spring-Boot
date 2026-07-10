package com.rag_spring_boot.controller;

import com.rag_spring_boot.service.FileReaderService;
import com.rag_spring_boot.service.ChunkingService; 
import com.rag_spring_boot.service.MetadataService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@RestController
public class FileReaderController {
    private final FileReaderService service = new FileReaderService();
    private final ChunkingService chunkingService = new ChunkingService();
    private final MetadataService metadataService = new MetadataService();

    @GetMapping("/baca")
    public String baca() throws Exception {
        return service.readFile("documents/sample.txt");
    }

    @GetMapping("/chunk")
    public List<String> chunk() throws Exception {
        
        String text = service.readFile("documents/sample.txt");
        
        return chunkingService.chunkText(text, 5);
    }

     @GetMapping("/metadata")
     public List<Map<String, Object>> metadata() throws Exception {  

        String text = service.readFile("documents/sample.txt");
        List<String> chunks = chunkingService.chunkText(text, 5);
        List<Map<String, Object>> result = new ArrayList<>();
        for (String chunk : chunks) {
            result.add(metadataService.createMetadata(chunk, "sample.txt"));
        }
        return result;
    }  

    
}

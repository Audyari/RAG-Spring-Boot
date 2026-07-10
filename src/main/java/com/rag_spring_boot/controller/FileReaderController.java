package com.rag_spring_boot.controller;

import com.rag_spring_boot.service.FileReaderService;
import com.rag_spring_boot.service.ChunkingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class FileReaderController {
    private final FileReaderService service = new FileReaderService();
    private final ChunkingService chunkingService = new ChunkingService();

    @GetMapping("/baca")
    public String baca() throws Exception {
        return service.readFile("documents/sample.txt");
    }

    @GetMapping("/chunk")
    public List<String> chunk() throws Exception {
        System.out.println("=== MASUK KE CHUNK ===");  // ← TARUH DI SINI (PERTAMA)
        
        System.out.println("Mencoba baca file...");     // ← TARUH DI SINI
        
        String text = service.readFile("documents/sample.txt");
        
        System.out.println("Isi file: " + text);        // ← BARU INI
        
        return chunkingService.chunkText(text, 5);
    }
}

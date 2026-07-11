package com.rag_spring_boot.controller;

import com.rag_spring_boot.service.FileReaderService;
import com.rag_spring_boot.service.ChunkingService; 
import com.rag_spring_boot.service.MetadataService;
import com.rag_spring_boot.service.VectorStoreService;
import com.rag_spring_boot.service.RouterService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;

@RestController
public class FileReaderController {
    
    private final FileReaderService fileReaderService;
    private final ChunkingService chunkingService;
    private final MetadataService metadataService;
    private final VectorStoreService vectorStoreService;
    private final RouterService routerService;

    public FileReaderController(FileReaderService fileReaderService,
                                ChunkingService chunkingService,
                                MetadataService metadataService,
                                VectorStoreService vectorStoreService,
                                RouterService routerService) {
        
        this.fileReaderService = fileReaderService;
        this.chunkingService = chunkingService;
        this.metadataService = metadataService;
        this.vectorStoreService = vectorStoreService;
        this.routerService = routerService;
    }

    @GetMapping("/baca")
    public String baca() throws Exception {
        return fileReaderService.readFile("documents/sample.txt");
    }

    @GetMapping("/chunk")
    public List<String> chunk() throws Exception {
        
        String text = fileReaderService.readFile("documents/sample.txt");
        
        return chunkingService.chunkText(text, 5);
    }

     @GetMapping("/metadata")
     public List<Map<String, Object>> metadata() throws Exception {  

        String text = fileReaderService.readFile("documents/sample.txt");
        List<String> chunks = chunkingService.chunkText(text, 5);
        List<Map<String, Object>> result = new ArrayList<>();
        for (String chunk : chunks) {
            result.add(metadataService.createMetadata(chunk, "sample.txt"));
        }
        return result;
    }  

    @GetMapping("/save")
    public Map<Long, Map<String, Object>> save() throws Exception {
         
        // Kalau udah ada data, langsung balikin (jangan save ulang)
        if (vectorStoreService.count() > 0) {
            System.out.println("⚠️ Data sudah ada, skip save!");
            return vectorStoreService.findAll();
        }
    
        System.out.println("💾 Menyimpan data...");
        String text = fileReaderService.readFile("documents/sample.txt");
        List<String> chunks = chunkingService.chunkText(text, 5);
        
        for (String chunk : chunks) {
            Map<String, Object> metadata = metadataService.createMetadata(chunk, "sample.txt");
            vectorStoreService.save(chunk, metadata);
        }
    
        System.out.println("✅ Selesai menyimpan " + vectorStoreService.count() + " data!");
        return vectorStoreService.findAll();    
    }

    @GetMapping("/vectors")
    public  Map<Long, Map <String, Object>> findAll(){
        return vectorStoreService.findAll();
    }

    @GetMapping("/vectors/count")
    public String count(){
        return "Total data: " + vectorStoreService.count();
    }

    @GetMapping("/vectors/{id}")
    public Map<String, Object> findById(@PathVariable Long id){
        return vectorStoreService.findById(id);
    }

     @DeleteMapping("/vectors")
     public String deleteAll(){
        vectorStoreService.deleteAll();
        return "Semua data dihapus!";
    }

    @GetMapping("/ask")
    public Map<String, Object> ask(@RequestParam String q) {
        Map<String, Object> response = new HashMap<>();
        response.put("query", q);
        response.put("route", routerService.route(q));
        response.put("timestamp", System.currentTimeMillis());

        String route = routerService.route(q);

        if (route.equals("greeting")) {
            response.put("message", "Halo! Ada yang bisa saya bantu? 😊");
        } else if (route.equals("calculator")) {
            response.put("message", "Saya bisa membantu perhitungan! 🔢");
        } else if (route.equals("rag")) {
            response.put("message", "🔍 Saya akan mencari di database RAG...");
        } else {
            response.put("message", "Saya akan mencari tahu... 🤔");
    }

        return response;
    }
}

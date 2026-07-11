package com.rag_spring_boot.controller;

import com.rag_spring_boot.service.FileReaderService;
import com.rag_spring_boot.service.ChunkingService; 
import com.rag_spring_boot.service.MetadataService;
import com.rag_spring_boot.service.VectorStoreService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
public class FileReaderController {
    private final FileReaderService fileReaderService = new FileReaderService();
    private final ChunkingService chunkingService = new ChunkingService();
    private final MetadataService metadataService = new MetadataService();
    private final VectorStoreService vectorStoreService = new VectorStoreService();

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
        String text = fileReaderService.readFile("documents/sample.txt");
        List<String> chunks= chunkingService.chunkText(text, 5);
        for (String chunk : chunks) {
            Map<String, Object> metadata = metadataService.createMetadata(chunk, "sample.txt");
            vectorStoreService.save(chunk,  metadata);
        }
        return  vectorStoreService.findAll();
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
}

package com.rag_spring_boot.controller;

import com.rag_spring_boot.service.FileReaderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileReaderController {
    private final FileReaderService service = new FileReaderService();

    @GetMapping("/baca")
    public String baca() throws Exception {
        return service.readFile("documents/sample.txt");
    }
}

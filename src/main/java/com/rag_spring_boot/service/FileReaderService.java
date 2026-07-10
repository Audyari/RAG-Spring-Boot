package com.rag_spring_boot.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileReaderService {
    public String readFile(String path) throws Exception {
        return Files.readString(Paths.get(new ClassPathResource(path).getURI()));
    }
}

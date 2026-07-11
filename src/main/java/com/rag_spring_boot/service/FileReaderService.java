package com.rag_spring_boot.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileReaderService {
    public String readFile(String path) throws Exception {

        ClassPathResource resource = new ClassPathResource(path);
        if (!resource.exists()){
             throw new RuntimeException("❌ File tidak ditemukan: " + path);
        }

        String text = Files.readString(Paths.get(resource.getURI()));
        System.out.println("✅ File berhasil dibaca: " + path);
        System.out.println("📝 Panjang teks awal: " + text.length() + " karakter");

        String cleaned = text.replaceAll("\\r\\n|\\r|\\n", " ")
                             .replaceAll("\\s+", " ")
                             .trim();

        System.out.println("📝 Panjang teks setelah clean: " + cleaned.length() + " karakter");
        return cleaned;
    }

}

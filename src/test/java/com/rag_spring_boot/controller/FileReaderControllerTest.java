package com.rag_spring_boot.controller;

import com.rag_spring_boot.service.FileReaderService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FileReaderControllerTest {

    @Test
    void testBacaFile() throws Exception {
        FileReaderService service = new FileReaderService();
        String result = service.readFile("documents/sample.txt");
        assertNotNull(result);
        assertTrue(result.contains("RAG"));
        System.out.println("Test berhasil! Isi file: " + result);
    }
}

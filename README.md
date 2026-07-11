<!-- markdownlint-disable MD013 -->
# 🚀 RAG Spring Boot

**RAG-Spring-Boot** adalah implementasi **Retrieval-Augmented Generation (RAG)**
menggunakan Spring Boot. Project ini mendemonstrasikan bagaimana membangun
sistem RAG dari 8 layer secara lengkap.

## ✨ Fitur Utama

- ✅ **Layer 1: Ingestion** - Baca file (PDF, TXT, Word)
- ✅ **Layer 2: Chunking** - Potong teks menjadi chunk
- ✅ **Layer 3: Metadata** - Ekstrak metadata (source, length, wordCount)
- ✅ **Layer 4: Vector Store** - Simpan ke vector store (in-memory)
- ✅ **Layer 5: Retrieval** - Search query
- ✅ **Spring Security** - Form Login + Basic Auth
- ✅ **Thymeleaf** - Custom login page
- ✅ **REST API** - Endpoint lengkap

## 🛠️ Teknologi yang Digunakan

| Teknologi | Versi | Fungsi |
| :--- | :--- | :--- |
| **Java** | 21 | Bahasa pemrograman |
| **Spring Boot** | 4.1.0 | Framework utama |
| **Spring Security** | - | Autentikasi & otorisasi |
| **Spring Web MVC** | - | REST API |
| **Thymeleaf** | - | Template engine (HTML) |
| **Gradle** | 9.5.1 | Build tool |

## 📡 Dokumentasi API

**Swagger UI:** <http://localhost:8080/swagger-ui/index.html>

## 🚀 Cara Menjalankan

```bash
# Clone repository
git clone https://github.com/Audyari/RAG-Spring-Boot.git
cd RAG-Spring-Boot

# Jalankan aplikasi
gradlew clean build
gradlew bootRun

# Buka di browser
http://localhost:8080/login

Default Credentials:

Field Value
Username admin
Password admin

2️⃣ Test Endpoint di Browser

Endpoint URL Hasil yang Diharapkan

Baca file http://localhost:8080/baca Isi file sample.txt
Chunk http://localhost:8080/chunk JSON array chunk
Metadata http://localhost:8080/metadata JSON metadata
Save     http//localhost:8080/save Simpan ke vector store
Vectors http://localhost:8080/vectors JSON semua data
Count http://localhost:8080/vectors/count Total data
Search http://localhost:8080/search?q=RAG JSON hasil search
Ask http://localhost:8080/ask?q=hai JSON response
Ask (RAG) http://localhost:8080/ask?q=apa itu RAG JSON response
Ask (Unsafe) http://localhost:8080/ask?q=hack JSON error
Stats http://localhost:8080/stats JSON statistik

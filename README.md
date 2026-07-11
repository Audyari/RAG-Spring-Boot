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

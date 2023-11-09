package com.github.shop.image;

import com.github.shop.image.dto.ResponseUploadImage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/images")
public class ImageController {

    private final ImageDataManager imageDataManager;

    @PostMapping("/upload-image")
    public ResponseEntity<ResponseUploadImage> uploadImage(@RequestParam("image") MultipartFile file) throws ImageNotSavedException {
        ResponseUploadImage responseImage = imageDataManager.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseImage);
    }

    @PostMapping("/download-image-from-url/{filename}")
    public ResponseEntity<ResponseUploadImage> downloadImageFromUrl(@PathVariable String filename) throws IOException, InterruptedException {
        ResponseUploadImage responseImage = imageDataManager.serveImageFromUrl(filename);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseImage);
    }

    @GetMapping("/download-image/{filename}")
    ResponseEntity<Resource> downloadImage(@PathVariable String filename) throws IOException {
        Resource resource = imageDataManager.serveFiles(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Path.of(filename)))
                .body(resource);
    }
}

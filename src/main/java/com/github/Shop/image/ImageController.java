package com.github.Shop.image;

import com.github.Shop.image.dto.ResponseUploadImage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/images")
public class ImageController {

    private final ImageDataManager imageDataManager;

    @PostMapping("/upload-image")
    public ResponseEntity<ResponseUploadImage> uploadImage(@RequestParam("image") MultipartFile file) {
        ResponseUploadImage responseImage = imageDataManager.uploadImage(file);

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseImage);
    }
}

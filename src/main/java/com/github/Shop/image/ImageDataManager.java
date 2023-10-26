package com.github.Shop.image;

import com.github.Shop.image.dto.ResponseUploadImage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
@AllArgsConstructor
@Log4j2
public class ImageDataManager {

    private final ImageDataRepository imageDataRepository;

    public ResponseUploadImage uploadImage(MultipartFile file) {
        try {
        Resource resource = file.getResource();
        File resourceFile = resource.getFile();

        if(!resourceFile.exists()){
            throw new FileNotFoundException();
        }
            ResponseUploadImage image = ResponseUploadImage.builder()
                    .path(resourceFile.getAbsolutePath())
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .size(file.getSize())
                    .lastModified(resourceFile.lastModified())
                    .build();

            ResponseUploadImage uploadImageSaved = imageDataRepository.save(image);

            String imageInfo = generateImageInfoMessage(uploadImageSaved);
            log.info(imageInfo);

            return getResponseUploadImage(uploadImageSaved);

        } catch (IOException e) {
            throw new RuntimeException("Upload image not found");
        }
    }

    private ResponseUploadImage getResponseUploadImage(ResponseUploadImage uploadImageSaved) {
        return ResponseUploadImage.builder()
                .name(uploadImageSaved.name())
                .path(uploadImageSaved.path())
                .type(uploadImageSaved.type())
                .size(uploadImageSaved.size())
                .lastModified(uploadImageSaved.lastModified())
                .build();
    }

    private String generateImageInfoMessage(ResponseUploadImage uploadImageSaved) {
        return String.format("Image uploaded successfully: " +
                        "Name: %s " +
                        "Type: %s " +
                        "Size: %s" +
                        "last modified: %s",
                uploadImageSaved.name(),
                uploadImageSaved.type(),
                uploadImageSaved.size(),
                uploadImageSaved.lastModified());
    }

}

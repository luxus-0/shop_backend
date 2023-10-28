package com.github.Shop.image;

import com.github.Shop.image.dto.ResponseUploadImage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@AllArgsConstructor
@Log4j2
public class ImageDataManager {

    private final ImageDataRepository imageDataRepository;

    public ResponseUploadImage uploadImage(MultipartFile file) {
            Resource resource = file.getResource();

        ResponseUploadImage image = ResponseUploadImage.builder()
                    .name(file.getName())
                    .path(resourceFile.getPath())
                    .absolutePath(resourceFile.getAbsolutePath())
                    .type(file.getContentType())
                    .build();

            ResponseUploadImage uploadImageSaved = imageDataRepository.save(image);

            String imageInfo = generateImageInfoMessage(uploadImageSaved);
            log.info(imageInfo);

            return getResponseUploadImage(uploadImageSaved);
    }

    private ResponseUploadImage getResponseUploadImage(ResponseUploadImage uploadImageSaved) {
        return ResponseUploadImage.builder()
                .name(uploadImageSaved.name())
                .path(uploadImageSaved.path())
                .absolutePath(uploadImageSaved.absolutePath())
                .type(uploadImageSaved.type())
                .build();
    }

    private String generateImageInfoMessage(ResponseUploadImage uploadImageSaved) {
        return String.format("Image uploaded successfully: " +
                        "Name: %s " +
                        "Path: %s " +
                        "Absolute Path: %s " +
                        "Type: %s ",
                uploadImageSaved.name(),
                uploadImageSaved.path(),
                uploadImageSaved.absolutePath(),
                uploadImageSaved.type());
    }

}

package com.github.Shop.image;

import com.github.Shop.image.dto.ResponseUploadImage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@AllArgsConstructor
@Log4j2
public class ImageDataManager {
    @Value("${image.path}")
    private String imagePath;

    public ResponseUploadImage uploadImage(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String changedName = UploadedFilesNameUtils.slugifyFileName(fileName);

        Path filePath = Paths.get(imagePath).resolve(changedName);

        try (InputStream inputStream = file.getInputStream()) {
            OutputStream outputStream = Files.newOutputStream(filePath);
            inputStream.transferTo(outputStream);
        } catch (IOException e) {
            throw new RuntimeException("The file cannot be saved", e);
        }
        return new ResponseUploadImage(fileName);
    }

    Resource serveFiles(String fileName){
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
       return fileSystemResourceLoader.getResource(imagePath + fileName);
    }
}

package com.github.Shop.image;

import com.github.Shop.image.dto.ResponseUploadImage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@Log4j2
public class ImageDataManager {
    @Value("${image.path}")
    private String imagePath;

    private final ImageDataRepository imageRepository;

    public ImageDataManager(ImageDataRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ResponseUploadImage uploadImage(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String changedFileName = UploadedFilesNameUtils.slugifyFileName(fileName);
        changedFileName = ExistingFileNameUtils.renameIfExists(Path.of(imagePath), changedFileName);

        Path filePath = Paths.get(imagePath).resolve(changedFileName);

        try (InputStream inputStream = file.getInputStream()) {
            OutputStream outputStream = Files.newOutputStream(filePath);
            inputStream.transferTo(outputStream);

        } catch (IOException e) {
            throw new RuntimeException("Image cannot be saved", e);
        }

        Image image = Image.builder()
                .name(changedFileName)
                .type(file.getContentType())
                .path(imagePath)
                .build();

        Image imageSaved = imageRepository.save(image);
        log.info("Image saved in database: \n" +
                "\nName: " + imageSaved.getName() +
                "\nPath: " + imageSaved.getPath() +
                "\nType: " + imageSaved.getType() +
                "\nSize: " + file.getSize() + "KB");

        return new ResponseUploadImage(fileName);
    }

    Resource serveFiles(String filename) throws IOException {
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        Resource resource = fileSystemResourceLoader.getResource(imagePath + filename);
        if (resource.getFile().exists()) {
            System.out.println("File downloaded successfully");
            return resource;
        }
        throw new FileNotFoundException("File not found");
    }
}

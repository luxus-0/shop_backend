package com.github.shop.domain.image;

import com.github.shop.domain.image.dto.ResponseUploadImage;
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
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Log4j2
public class ImageDataManager {
    private final ImageDataRepository imageRepository;
    @Value("${image.path}")
    private String imagePath;

    public ImageDataManager(ImageDataRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ResponseUploadImage uploadImage(MultipartFile file) throws ImageNotSavedException {
        String fileName = file.getOriginalFilename();
        String changedFileName = UploadedFilesNameUtils.slugifyFileName(fileName);
        changedFileName = ExistingFileNameUtils.renameIfExists(Path.of(imagePath), changedFileName);
        Path filePath = Paths.get(imagePath).resolve(changedFileName);
        saveImageToFile(file, filePath);
        Image image = createImage(file, changedFileName);
        logImageDetails(file, image);
        return new ResponseUploadImage(fileName);
    }

    private void logImageDetails(MultipartFile file, Image image) {
        Image imageSaved = imageRepository.save(image);
        log.info("Image saved in database: \n" +
                "\nName: " + imageSaved.getName() +
                "\nPath: " + imageSaved.getPath() +
                "\nType: " + imageSaved.getType() +
                "\nSize: " + file.getSize() + "KB");
    }

    private Image createImage(MultipartFile file, String changedFileName) {
        return Image.builder()
                .name(changedFileName)
                .type(file.getContentType())
                .path(imagePath)
                .build();
    }

    private static void saveImageToFile(MultipartFile file, Path filePath) throws ImageNotSavedException {
        try (InputStream inputStream = file.getInputStream()) {
            OutputStream outputStream = Files.newOutputStream(filePath);
            inputStream.transferTo(outputStream);

        } catch (IOException e) {
            throw new ImageNotSavedException("Image cannot be saved");
        }
    }

    Resource downloadImage(String filename) throws IOException {
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        Resource resource = fileSystemResourceLoader.getResource(imagePath + filename);
        if (resource.getFile().exists()) {
            log.info("File downloaded successfully");
            return resource;
        }
        throw new FileNotFoundException("File not found");
    }

    ResponseUploadImage downloadImage(String fileName, String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        String changedFileName = UploadedFilesNameUtils.slugifyFileName(fileName);
        Path path = Path.of(imagePath, changedFileName);

        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
        isOkStatusCodeForDownloadedImage(response, path);
        return new ResponseUploadImage(fileName);
    }

    private static void isOkStatusCodeForDownloadedImage(HttpResponse<InputStream> response, Path path) throws IOException {
        if (response.statusCode() == 200) {
            log.info("File downloaded successfully");
            InputStream in = response.body();
            OutputStream out = Files.newOutputStream(path);

            int bytesRead;
            while ((bytesRead = in.read()) != -1) {
                out.write(bytesRead);
            }
        } else {
            throw new FileNotFoundException();
        }
    }
}
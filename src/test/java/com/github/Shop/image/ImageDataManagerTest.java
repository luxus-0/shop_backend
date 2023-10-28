package com.github.Shop.image;

import com.github.Shop.image.dto.ResponseUploadImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImageDataManagerTest {

    @Mock
    private ImageDataRepository imageDataRepository;
    @InjectMocks
    private ImageDataManager imageDataManager;

    @Test
    public void testUploadImage() {
        ResponseUploadImage image = ResponseUploadImage.builder()
                .id(1L)
                .name("Chicken.jpg")
                .absolutePath("C://Users//nowog//OneDrive//Pulpit//Shop//src//main//resources")
                .type("application/json")
                .build();

        when(imageDataRepository.save(any(ResponseUploadImage.class))).thenReturn(image);
        ResponseUploadImage uploadImageSaved = imageDataRepository.save(image);

        MultipartFile file = new MockMultipartFile(uploadImageSaved.name(), uploadImageSaved.absolutePath(), uploadImageSaved.type(), new byte[]{1,2,3});

        ResponseUploadImage uploadImage = imageDataManager.uploadImage(file);


        assertNotNull(uploadImage);
        //assertEquals("test.jpg", uploadImage.name());
    }

}
package com.github.Shop.image;

import com.github.Shop.image.dto.ResponseUploadImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ImageDataRepository extends JpaRepository<ResponseUploadImage, Long> {
}

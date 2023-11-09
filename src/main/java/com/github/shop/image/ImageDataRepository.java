package com.github.shop.image;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDataRepository extends JpaRepository<Image, Long> {
}

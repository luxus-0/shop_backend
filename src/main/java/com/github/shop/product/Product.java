package com.github.shop.product;

import com.github.shop.currency.Currency;
import com.github.shop.image.Image;
import com.github.shop.review.Review;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long categoryId;
    private BigDecimal price;
    @Enumerated(value = EnumType.STRING)
    private Currency currency;
    private String description;
    private String fullDescription;
    private String slug;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private List<Image> images;
    @OneToMany
    @JoinColumn(name = "productId")
    private List<Review> reviews;
}

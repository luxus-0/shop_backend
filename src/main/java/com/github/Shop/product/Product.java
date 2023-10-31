package com.github.Shop.product;

import com.github.Shop.currency.Currency;
import com.github.Shop.image.Image;
import com.github.Shop.review.Review;
import jakarta.persistence.*;
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

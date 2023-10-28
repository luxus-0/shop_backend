package com.github.Shop.product;

import com.github.Shop.currency.Currency;
import com.github.Shop.image.Image;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private BigDecimal price;
    @Enumerated(value = EnumType.STRING)
    private Currency currency;
    private String description;
    @OneToMany(mappedBy = "product")
    private List<Image> image;
    private String slug;
}

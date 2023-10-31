package com.github.Shop.adminproduct;

import com.github.Shop.currency.Currency;
import com.github.Shop.image.Image;
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
@Table(name = "admin_product")
public class AdminProduct {
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
    @OneToMany
    @JoinColumn(name = "admin_product_id")
    private List<Image> images;
    private String slug;
}

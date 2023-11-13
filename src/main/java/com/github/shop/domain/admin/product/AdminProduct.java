package com.github.shop.domain.admin.product;

import com.github.shop.domain.currency.Currency;
import com.github.shop.domain.image.Image;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String name;
    @NotBlank
    private Long categoryId;
    @NotBlank
    private BigDecimal price;
    @NotBlank
    @Enumerated(value = EnumType.STRING)
    private Currency currency;
    @NotBlank
    private String description;
    @NotBlank
    private String fullDescription;
    @OneToMany
    @JoinColumn(name = "admin_product_id")
    private List<Image> images;
    @NotBlank
    private String slug;
}

package com.github.Shop.admin;

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
public class AdminProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private BigDecimal price;
    @Enumerated(value = EnumType.STRING)
    private Currency currency;
    private String description;
    @OneToMany(mappedBy = "adminProduct")
    private List<Image> image;
    private String slug;
}

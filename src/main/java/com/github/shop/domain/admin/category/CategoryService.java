package com.github.shop.domain.admin.category;

import com.github.shop.domain.admin.category.dto.CategoryProductsDto;
import com.github.shop.domain.admin.category.dto.CategoryDto;
import com.github.shop.domain.product.Product;
import com.github.shop.domain.product.ProductRepository;
import com.github.slugify.Slugify;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public List<Category> readCategories() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CategoryProductsDto readCategoriesWithProducts(String slug, Pageable pageable) {
        Category category = categoryRepository.findBySlug(slug);
        Page<Product> product = productRepository.findByCategoryId(category.getId(), pageable);
        return new CategoryProductsDto(category, product);
    }

    public Category readCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    public Category saveCategory(CategoryDto categoryDto) {
        return categoryRepository.save(Category.builder()
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .slug(slugifyCategoryName(categoryDto.getSlug()))
                .build());
    }

    public Category updateCategory(CategoryDto categoryDto, Long id) {
        return Category.builder()
                .id(id)
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .slug(slugifyCategoryName(categoryDto.getSlug()))
                .build();
    }

    private String slugifyCategoryName(String slug) {
        return Slugify.builder()
                .customReplacement("_", "-")
                .build()
                .slugify(slug);
    }

    public void removeCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}

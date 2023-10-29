package com.github.Shop.category;

import com.github.Shop.category.dto.CategoryDto;
import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class CategoryService {

    private final CategoryRepository categoryRepository;
    public List<Category> readCategories() {
        return categoryRepository.findAll();
    }

    public Category readCategoriesWithProducts(String slug) {
        return categoryRepository.findBySlug(slug);
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
                .customReplacement("_","-")
                .build()
                .slugify(slug);
    }

    public void removeCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}

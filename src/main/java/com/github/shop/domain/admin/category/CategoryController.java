package com.github.shop.domain.admin.category;

import com.github.shop.domain.admin.category.dto.CategoryDto;
import com.github.shop.domain.admin.category.dto.CategoryProductsDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/categories")
class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.readCategories();
    }

    @GetMapping("/{slug}/products")
    public CategoryProductsDto getCategoriesWithProducts(@PathVariable
                                                         @Pattern(regexp = "[a-z0-9\\-]+")
                                                         @Length(max = 255) String slug, Pageable pageable) {
        return categoryService.readCategoriesWithProducts(slug, pageable);
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id) {
        return categoryService.readCategory(id);
    }

    @PostMapping
    public Category createCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return categoryService.saveCategory(categoryDto);
    }

    @PutMapping("/{id}")
    Category updateCategory(@RequestBody @Valid CategoryDto categoryDto, @PathVariable Long id) {
        return categoryService.updateCategory(categoryDto, id);
    }

    @DeleteMapping("/{id}")
    void deleteCategory(@PathVariable Long id) {
        categoryService.removeCategory(id);

    }
}

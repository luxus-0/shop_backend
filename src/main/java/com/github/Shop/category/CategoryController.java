package com.github.Shop.category;

import com.github.Shop.category.dto.CategoryDto;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/categories")
class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getCategories(){
        return categoryService.readCategories();
    }

    @GetMapping("/{slug}/products")
    public Category getCategoriesWithProducts(@PathVariable
                                              @Pattern(regexp = "[a-z0-9\\-]+")
                                              @Length(max = 255) String slug){
        return categoryService.readCategoriesWithProducts(slug);
    }
    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id){
        return categoryService.readCategory(id);
    }

    @PostMapping
    public Category createCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.saveCategory(categoryDto);
    }

    @PutMapping("/{id}")
    Category updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Long id){
        return categoryService.updateCategory(categoryDto, id);
    }

    @DeleteMapping("/{id}")
    void deleteCategory(@PathVariable Long id){
        categoryService.removeCategory(id);

    }
}

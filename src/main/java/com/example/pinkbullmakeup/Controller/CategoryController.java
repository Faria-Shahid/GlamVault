package com.example.pinkbullmakeup.Controller;

import com.example.pinkbullmakeup.Entity.Category;
import com.example.pinkbullmakeup.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestParam String categoryName) {
        Category category = categoryService.addCategory(categoryName);
        return ResponseEntity.created(URI.create("/api/categories/" + category.getCategoryId())).body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@Valid @RequestParam String newCategoryName, @PathVariable UUID id) {
        Category updatedCategory = categoryService.updateCategory(newCategoryName, id);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/search")
    public ResponseEntity<Category> getCategoryByName(@RequestParam String categoryName) {
        Category category = categoryService.findByName(categoryName);
        return ResponseEntity.ok(category);
    }
}


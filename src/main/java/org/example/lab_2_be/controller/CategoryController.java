package org.example.lab_2_be.controller;


import jakarta.validation.Valid;
import org.example.lab_2_be.service.CategoryService;
import org.example.lab_2_be.dto.CategoryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryDto category) {
        int categoryId = categoryService.addCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Category with ID: %d was added", categoryId));
    }
}

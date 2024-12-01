package org.example.lab_3_be.service;

import org.example.lab_3_be.dto.CategoryDto;
import org.example.lab_3_be.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(CategoryDto::fromCategory).toList();
    }

    public Optional<CategoryDto> getCategoryById(int id) {
        return categoryRepository.findById(id).map(CategoryDto::fromCategory);
    }

    public int addCategory(CategoryDto categoryDto) {
        var category = CategoryDto.toCategoryEntity(categoryDto);
        categoryRepository.save(category);
        return category.getId();
    }
}

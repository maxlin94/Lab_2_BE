package org.example.lab_2_be.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.lab_2_be.entities.CategoryEntity;

public record CategoryDto(
        @NotBlank(message = "Name must not be blank")
        String name,
        @NotBlank(message = "Symbol must not be blank")
        @Size(min = 1, max = 1, message = "Symbol must be 1 character long")
        String symbol,
        @NotBlank(message = "Description must not be blank")
        String description
) {
    public static CategoryDto fromCategory(CategoryEntity categoryEntity) {
        return new CategoryDto(categoryEntity.getName(), categoryEntity.getSymbol(), categoryEntity.getDescription());
    }

    public static CategoryEntity toCategoryEntity(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryDto.name());
        categoryEntity.setSymbol(categoryDto.symbol());
        categoryEntity.setDescription(categoryDto.description());
        return categoryEntity;
    }
}

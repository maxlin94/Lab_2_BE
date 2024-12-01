package org.example.lab_3_be.repository;

import org.example.lab_3_be.entities.CategoryEntity;
import org.springframework.data.repository.ListCrudRepository;


public interface CategoryRepository extends ListCrudRepository<CategoryEntity, Integer> {
    CategoryEntity findByName(String name);
}
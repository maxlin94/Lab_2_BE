package org.example.lab_3_be.category;


import jdk.jfr.Category;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping
    public String getAllCategories() {
        return "Hello world";
    }

    @GetMapping("/{id}")
    public String getCategoryById(@PathVariable int id) {
        return "Hello world" + id;
    }

    @PostMapping
    public String addCategory(@RequestBody Category category) {
        return "Hello world" + category;
    }
}

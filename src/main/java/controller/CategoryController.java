package controller;



import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Category createCategory(@RequestParam String name) {
        return categoryService.createCategory(name);
    }

    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAllCategories();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}

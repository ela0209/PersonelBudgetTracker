package service;

// CategoryService.java


import exception.ResourceNotFoundException;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(String name) {
        return categoryRepository.save(new Category(name));
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Kategori bulunamadı: " + name));
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kategori bulunamadı. ID: " + id));
        categoryRepository.delete(category);
    }
}


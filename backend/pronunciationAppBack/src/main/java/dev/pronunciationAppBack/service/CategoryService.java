package dev.pronunciationAppBack.service;

import dev.pronunciationAppBack.model.Category;
import dev.pronunciationAppBack.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(String id) {
        return Optional.ofNullable(categoryRepository.getCategoryById(id));
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }

    public void deleteAllCategories() {
        categoryRepository.deleteAll();
    }

    public boolean existsById(String id) {
        return categoryRepository.existsById(id);
    }

    public long getCategoryCount() {
        return categoryRepository.count();
    }

    // Additional business logic can be added here
    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoryByCategoryName(name);
    }
}
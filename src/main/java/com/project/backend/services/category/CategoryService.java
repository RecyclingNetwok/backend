package com.project.backend.services.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<Category> getAllCategorys() {
		return (List<Category>) categoryRepository.findAll();
	}

	public Category getCategoryByID(Long id) {
		return categoryRepository.findById(id).get();
	}

	public void addNewCategory(Category category) {
		Optional<Category> categoryOptional = categoryRepository.findByName(category.getName());
		if (categoryOptional.isPresent()) {
			throw new IllegalStateException("Category Already Exist !");
		} else {
			System.out.println(category);
			categoryRepository.save(category);
		}
	}

	public void deleteCategory(Long id) {
		boolean exists = categoryRepository.existsById(id);
		if (!exists) {
			throw new IllegalStateException("Category with id '" + id + "' does not exists !");
		}
		categoryRepository.deleteById(id);
		System.out.println("Deleting category...");
	}

	// TODO updateCategory(Long id)
}

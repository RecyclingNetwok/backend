package com.project.backend.services.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.login.controllers.AuthConfigService;
import com.project.backend.login.request.SignupRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
	
	private final CategoryService categoryService;
	
	@Autowired
	AuthConfigService configService;
	
	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping
	public List<Category> getAllCategorys(){
		return categoryService.getAllCategorys();
	}
	
	@GetMapping("-{CategoryID}")
	public Category getCategoryById(@PathVariable("CategoryID") Long id) {
		return categoryService.getCategoryByID(id);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addNewCategory(@RequestBody SignupRequest category) {
		return configService.signUp(category);
	}
	
	@DeleteMapping("-{CategoryID}")
	public void deletePost(@PathVariable("CategoryID") Long id) {
		categoryService.deleteCategory(id);
	}
	
}

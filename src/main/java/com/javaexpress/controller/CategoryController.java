package com.javaexpress.controller;



import com.javaexpress.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.javaexpress.models.Category;
import com.javaexpress.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("api/v1/category")
public class CategoryController {
	
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Category createCategory(@RequestBody Category category) {
		log.info(" CategoryController :: createCategory {} ",category.getName());
		return categoryService.createCategoryInfo(category);
		
	}

	@GetMapping("{categoryId}")
	public Category getCategoryById(@PathVariable Long categoryId) {
		log.info(" CategoryController :: createCategory {} ");
		return categoryService.findByCategoryId(categoryId);

	}
	@PutMapping("{categoryId}")
	public Category updateCategory(@RequestBody Category category, @PathVariable Long categoryId ) {
		return categoryService.updateCategory(categoryId,category);
	}
	@DeleteMapping("{categoryId}")
	@ResponseStatus(HttpStatus.NO_CONTENT) //204
	public void deleteCategory(@PathVariable Long categoryId) {
		categoryService.deleteCategory(categoryId);
	}
	}

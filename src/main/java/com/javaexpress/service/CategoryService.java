package com.javaexpress.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.models.Category;
import com.javaexpress.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public Category createCategoryInfo(Category category) {
		log.info(" CategoryService :: createCategoryInfo {} ");
	return	categoryRepository.save(category);

	}

	public Category findByCategoryId(Long categoryId) {
		return	categoryRepository.findById(categoryId).
				orElseThrow(()->new EntityNotFoundException("category not found"));
	}

	public Category updateCategory(Long categoryId, Category inputCategory) {
		return categoryRepository.findById(categoryId).map(category->{
			category.setName(inputCategory.getName());
			category.setProducts(inputCategory.getProducts());
			return categoryRepository.save(category);
		}).orElseThrow( ()->new EntityNotFoundException("category not found with id" +categoryId));
	}

	public void deleteCategory(Long categoryId) {
		if(categoryRepository.existsById(categoryId)){
			categoryRepository.deleteById(categoryId);
		}else{
			throw new EntityNotFoundException("Category not found "+categoryId);
		}
	}
}

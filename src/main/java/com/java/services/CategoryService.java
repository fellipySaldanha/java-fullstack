package com.java.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.domain.Category;
import com.java.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public Category findById(Integer id) {
		Optional<Category> category = repository.findById(id);
		return category.orElse(null);
	}
	
}

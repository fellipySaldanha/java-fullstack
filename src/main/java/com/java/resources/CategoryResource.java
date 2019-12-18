package com.java.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.domain.Category;
import com.java.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;
	
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable Integer id){
    	Category category = service.findById(id);
    	return ResponseEntity.ok().body(category);
    	
		/*
		 * Category cat1 = new Category(1, "informatica"); Category cat2 = new
		 * Category(2, "escritorio"); List<Category> listCategory = new ArrayList<>();
		 * listCategory.add(cat1); listCategory.add(cat2); return listCategory;
		 */
    }
    
}
package com.java.services;

import java.util.List;
import java.util.Optional;

import com.java.domain.Category;
import com.java.dto.CategoryDTO;
import com.java.repositories.CategoryRepository;
import com.java.services.exceptions.DataIntegrityException;
import com.java.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public Category findById(Integer id) {
		Optional<Category> category = repository.findById(id);
		return category.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));		
	}
	
	public Category insert(Category category) {
		category.setId(null);
		return repository.save(category);
	}

	public Category update(Category category) {
		this.findById(category.getId());
		return repository.save(category);
	}

	public void deleteById(Integer id) {
		this.findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}		
	}

	public List<Category> findAll() {
		return repository.findAll();		
	}

	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Category fromDTO(CategoryDTO category){
		return new Category(category.getId(), category.getName());
	}
}

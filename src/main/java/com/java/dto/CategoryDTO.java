package com.java.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.java.domain.Category;

import org.hibernate.validator.constraints.Length;

public class CategoryDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;

	@NotEmpty(message = "Preenchimento obrgatório")
	@Length(min = 5, max = 80, message = "O tamanha deve ser entre 5 e 80 carecteres")
    private String name;

    public CategoryDTO() {	
	}
    
    public CategoryDTO(Category category) {
    	this.id = category.getId();
    	this.name = category.getName();
   	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
        
}

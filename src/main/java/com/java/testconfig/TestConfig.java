package com.java.testconfig;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.java.domain.Category;
import com.java.repositories.CategoryRepository;

@Configuration
public class TestConfig implements CommandLineRunner{
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
			
		categoryRepository.saveAll(Arrays.asList(cat1,cat2));
		
	}
}

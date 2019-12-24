package com.java.testconfig;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.java.domain.Address;
import com.java.domain.Category;
import com.java.domain.City;
import com.java.domain.Client;
import com.java.domain.Product;
import com.java.domain.State;
import com.java.domain.enums.ClientType;
import com.java.repositories.AdressRepository;
import com.java.repositories.CategoryRepository;
import com.java.repositories.CityRepository;
import com.java.repositories.ClientRepository;
import com.java.repositories.ProductRepository;
import com.java.repositories.StateRepository;

@Configuration
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	AdressRepository adressRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1,p2));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p1.getCategories().addAll(Arrays.asList(cat2));
				
		categoryRepository.saveAll(Arrays.asList(cat1,cat2));
		productRepository.saveAll(Arrays.asList(p1,p2));
		
		State state1 = new State(null, "Minas Gerais");
		State state2 = new State(null, "São Paulo");
		
		City city1 = new City(null, "Uberlândia", state1);
		City city2 = new City(null, "São Paulo", state2);
		
		state1.getCities().addAll(Arrays.asList(city1));
		state2.getCities().addAll(Arrays.asList(city2));
		
		stateRepository.saveAll(Arrays.asList(state1,state2));
		cityRepository.saveAll(Arrays.asList(city1,city2));
		
		Client client1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.PESSOAFISICA);
		client1.getPhones().addAll(Arrays.asList("27363323", "93838393"));
		
		Address adress1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", client1, city1);
		Address adress2 = new Address(null, "Avenida Matos", "800", "Sala 103", "Centro", "38220834", client1, city2);
		
		client1.getAdresses().addAll(Arrays.asList(adress1, adress2));
		
		clientRepository.saveAll(Arrays.asList(client1));
		adressRepository.saveAll(Arrays.asList(adress1, adress2));
	}
}

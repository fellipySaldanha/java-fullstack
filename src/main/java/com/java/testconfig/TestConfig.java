package com.java.testconfig;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.java.domain.Address;
import com.java.domain.Category;
import com.java.domain.City;
import com.java.domain.Client;
import com.java.domain.Order;
import com.java.domain.OrderItem;
import com.java.domain.Payment;
import com.java.domain.PaymentBankBill;
import com.java.domain.PaymentCard;
import com.java.domain.Product;
import com.java.domain.State;
import com.java.domain.enums.ClientType;
import com.java.domain.enums.PaymentStatus;
import com.java.repositories.AddressRepository;
import com.java.repositories.CategoryRepository;
import com.java.repositories.CityRepository;
import com.java.repositories.ClientRepository;
import com.java.repositories.OrderItemRepository;
import com.java.repositories.OrderRepository;
import com.java.repositories.PaymentRepository;
import com.java.repositories.ProductRepository;
import com.java.repositories.StateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

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
	AddressRepository adressRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Cama mesa e banho");
		Category cat4 = new Category(null, "Eletrônicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");

		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1,p2));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p1.getCategories().addAll(Arrays.asList(cat2));
				
		categoryRepository.saveAll(Arrays.asList(cat1,cat2, cat3, cat4, cat5, cat6, cat7));
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
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
		Order order1 = new Order(null, simpleDateFormat.parse("30/09/2019 10:32"), client1, adress1);
		Order order2 = new Order(null, simpleDateFormat.parse("10/10/2019 10:32"), client1, adress2);  
	
		Payment payment1 = new PaymentCard(null, PaymentStatus.PAID, order1, 6);
		order1.setPayment(payment1);
		
		Payment payment2 = new PaymentBankBill(null, PaymentStatus.PENDING, order2, simpleDateFormat.parse("20/10/2019 00:00"), null);
		order2.setPayment(payment2);
		
		client1.getOrders().addAll(Arrays.asList(order1, order2));
		
		orderRepository.saveAll(Arrays.asList(order1, order2));
		paymentRepository.saveAll(Arrays.asList(payment1, payment2));
		
		OrderItem orderItem1 = new OrderItem(order1, p1, 0.00, 1, 2000.00);
		OrderItem orderItem2 = new OrderItem(order1, p2, 0.00, 2, 1600.00);
		OrderItem orderItem3 = new OrderItem(order2, p2, 100.00, 1, 800.00);
		
		order1.getItems().addAll(Arrays.asList(orderItem1, orderItem2));
		order2.getItems().addAll(Arrays.asList(orderItem3));
		
		p1.getItems().addAll(Arrays.asList(orderItem1));
		p2.getItems().addAll(Arrays.asList(orderItem2, orderItem3));
		
		orderItemRepository.saveAll(Arrays.asList(orderItem1,orderItem2, orderItem3));
		
		
	}
}

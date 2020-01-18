package com.java.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.java.domain.Address;
import com.java.domain.Category;
import com.java.domain.City;
import com.java.domain.Client;
import com.java.domain.Order;
import com.java.domain.OrderItem;
import com.java.domain.Payment;
import com.java.domain.PaymentCard;
import com.java.domain.PaymentSlip;
import com.java.domain.Product;
import com.java.domain.State;
import com.java.domain.enums.ClientType;
import com.java.domain.enums.PaymentStatus;
import com.java.domain.enums.Profile;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DBService{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncode;

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

	
	public void instantiateTestDatabase() throws ParseException{

		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Cama mesa e banho");
		Category cat4 = new Category(null, "Eletrônicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");		

		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Mesa de escritório", 300.00);
		Product p5 = new Product(null, "Toalha", 50.00);
		Product p6 = new Product(null, "Colcha", 200.00);
		Product p7 = new Product(null, "TV true color", 1200.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajour", 100.00);
		Product p10 = new Product(null, "Pendente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));
		
		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));		

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		State state1 = new State(null, "Minas Gerais");
		State state2 = new State(null, "São Paulo");

		City city1 = new City(null, "Uberlândia", state1);
		City city2 = new City(null, "São Paulo", state2);

		state1.getCities().addAll(Arrays.asList(city1));
		state2.getCities().addAll(Arrays.asList(city2));

		stateRepository.saveAll(Arrays.asList(state1, state2));
		cityRepository.saveAll(Arrays.asList(city1, city2));

		Client client1 = new Client(null, "Maria Silva", "fellipy.saldanha@gmail.com", "56109429007", ClientType.PESSOAFISICA, passwordEncode.encode("123"));
		client1.getPhones().addAll(Arrays.asList("27363323", "93838393"));
		
		Client client2 = new Client(null, "Ana Costa", "ana.saldanha@gmail.com", "41417979011", ClientType.PESSOAFISICA, passwordEncode.encode("123"));
		client2.getPhones().addAll(Arrays.asList("27363323", "93838393"));
		client2.addProfile(Profile.ADMIN);
		

		Address adress1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", client1, city1);
		Address adress2 = new Address(null, "Avenida Matos", "800", "Sala 103", "Centro", "38220834", client1, city2);
		Address adress3 = new Address(null, "Avenida Teste", "100", "Sala 1", "Copacabana", "38229994", client2, city2);

		client1.getAdresses().addAll(Arrays.asList(adress1, adress2));
		client2.getAdresses().addAll(Arrays.asList(adress3));

		clientRepository.saveAll(Arrays.asList(client1,client2));
		adressRepository.saveAll(Arrays.asList(adress1, adress2));

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Order order1 = new Order(null, simpleDateFormat.parse("30/09/2019 10:32"), client1, adress1);
		Order order2 = new Order(null, simpleDateFormat.parse("10/10/2019 10:32"), client1, adress2);

		Payment payment1 = new PaymentCard(null, PaymentStatus.PAID, order1, 6);
		order1.setPayment(payment1);

		Payment payment2 = new PaymentSlip(null, PaymentStatus.PENDING, order2,
				simpleDateFormat.parse("20/10/2019 00:00"), null);
		order2.setPayment(payment2);

		client1.getOrders().addAll(Arrays.asList(order1, order2));

		orderRepository.saveAll(Arrays.asList(order1, order2));
		paymentRepository.saveAll(Arrays.asList(payment1, payment2));

		OrderItem orderItem1 = new OrderItem(order1, p1, 0.00, 1, 2000.00);
		OrderItem orderItem2 = new OrderItem(order1, p2, 0.00, 2, 800.00);
		OrderItem orderItem3 = new OrderItem(order2, p2, 100.00, 1, 800.00);

		order1.getItems().addAll(Arrays.asList(orderItem1, orderItem2));
		order2.getItems().addAll(Arrays.asList(orderItem3));

		p1.getItems().addAll(Arrays.asList(orderItem1));
		p2.getItems().addAll(Arrays.asList(orderItem2, orderItem3));

		orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3));

	}
}

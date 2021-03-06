package com.java.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.domain.Client;
import com.java.domain.Order;
import com.java.domain.OrderItem;
import com.java.domain.PaymentSlip;
import com.java.domain.enums.PaymentStatus;
import com.java.repositories.OrderItemRepository;
import com.java.repositories.OrderRepository;
import com.java.repositories.PaymentRepository;
import com.java.security.UserSpringSecurity;
import com.java.services.exceptions.AuthorizationException;
import com.java.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;

	@Autowired
	private PaymentSlipService paymentSlipService;

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private EmailService emailService;

	public Order findById(Integer id) {
		Optional<Order> category = repository.findById(id);
		return category.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Order.class.getName()));		
	}
	
	@Transactional
	public Order insert(Order order) {
		order.setId(null);
		order.setInstant(new Date());
		order.setClient(clientService.findById(order.getClient().getId()));
		order.getPayment().setStatus(PaymentStatus.PENDING);
		order.getPayment().setOrder(order);
		if (order.getPayment() instanceof PaymentSlip) {
			PaymentSlip paymentSlip = (PaymentSlip) order.getPayment();
			paymentSlipService.createBankBill(paymentSlip, order.getInstant());
		}
		order = repository.save(order);
		paymentRepository.save(order.getPayment());

		for (OrderItem orderItem : order.getItems()) {
			orderItem.setDeal(0.0);
			orderItem.setProduct(productService.findById(orderItem.getProduct().getId()));
			orderItem.setPrice(orderItem.getProduct().getPrice());		
			orderItem.setOrder(order);
		}

		orderItemRepository.saveAll(order.getItems());
		emailService.sendOrderConfirmationHtmlEmail(order);
		return order;
	}
	
	public Page<Order> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		UserSpringSecurity user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Client client = clientService.findById(user.getId());
		return repository.findByClient(client, pageRequest);
	}
	
}

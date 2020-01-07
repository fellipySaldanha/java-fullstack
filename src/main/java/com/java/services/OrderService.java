package com.java.services;

import java.util.Date;
import java.util.Optional;

import com.java.domain.Order;
import com.java.domain.OrderItem;
import com.java.domain.PaymentSlip;
import com.java.domain.enums.PaymentStatus;
import com.java.repositories.OrderItemRepository;
import com.java.repositories.OrderRepository;
import com.java.repositories.PaymentRepository;
import com.java.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public Order findById(Integer id) {
		Optional<Order> category = repository.findById(id);
		return category.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Order.class.getName()));		
	}
	
	@Transactional
	public Order insert(Order order) {
		order.setId(null);
		order.setInstant(new Date());
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
			orderItem.setPrice(productService.findById(orderItem.getProduct().getId()).getPrice());
			orderItem.setOrder(order);
		}

		orderItemRepository.saveAll(order.getItems());
		return order;
	}
	
}

package com.java.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.domain.Order;
import com.java.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

	@Autowired
	private OrderService service;
	
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Order> findById(@PathVariable Integer id){
    	Order category = service.findById(id);
    	return ResponseEntity.ok().body(category);
    			
    }
    
}
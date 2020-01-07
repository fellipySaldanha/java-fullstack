package com.java.resources;

import java.net.URI;

import javax.validation.Valid;

import com.java.domain.Order;
import com.java.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Order order){        
    	order = service.insert(order);
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();
    	return ResponseEntity.created(uri).build();
    }
    
}
package com.java.resources;

import java.util.List;

import com.java.domain.Product;
import com.java.dto.ProductDTO;
import com.java.resources.utils.URL;
import com.java.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService service;
	
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> findById(@PathVariable Integer id){
    	Product category = service.findById(id);
    	return ResponseEntity.ok().body(category);    			
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> findPage(
		@RequestParam(value="name", defaultValue="") String name, 
		@RequestParam(value="categories", defaultValue="") String categories, 
		@RequestParam(value="page", defaultValue="0") Integer page, 
		@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
		@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
		@RequestParam(value="direction", defaultValue="ASC") String direction) {

			String decodeName = URL.decodeParameters(name);
			List<Integer> ids = URL.decodeIntList(categories);	
			Page<Product> products = service.search(decodeName, ids, page, linesPerPage, orderBy, direction);
			Page<ProductDTO> listDtos = products.map(product -> new ProductDTO(product));
			return ResponseEntity.ok().body(listDtos);    			
    }
    
}
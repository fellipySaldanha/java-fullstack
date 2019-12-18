package com.java.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}

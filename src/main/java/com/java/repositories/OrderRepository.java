package com.java.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}

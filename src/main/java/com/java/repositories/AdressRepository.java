package com.java.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.domain.Address;

@Repository
public interface AdressRepository extends JpaRepository<Address, Integer>{

}

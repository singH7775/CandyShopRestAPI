package com.CandyShop.CandyShop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.CandyShop.CandyShop.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
}

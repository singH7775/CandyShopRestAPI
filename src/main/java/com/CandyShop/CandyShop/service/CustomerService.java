package com.CandyShop.CandyShop.service;

import java.util.List;

import com.CandyShop.CandyShop.entity.Candy;
import com.CandyShop.CandyShop.entity.Customer;


public interface CustomerService {
	Customer getCustomer(Long id);
	Customer getCustomer(String name);
	Candy getCandy(Long id);
	Customer saveCustomer(Customer customer);
	void deleteCustomer(Long id);
	List<Customer> getCustomers();
	void orderACandy(Long customerId, Long candyId);
}

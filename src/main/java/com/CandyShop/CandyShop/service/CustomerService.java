package com.CandyShop.CandyShop.service;

import java.util.List;

import com.CandyShop.CandyShop.entity.Customer;

public interface CustomerService {
	Customer getCustomer(Long id);
	Customer saveCustomer(Customer customer);
	void deleteCustomer(Long id);
	List<Customer> getCustomers();
}

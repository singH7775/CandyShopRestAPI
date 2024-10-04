package com.CandyShop.CandyShop.service;

import java.util.List;


import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.CandyShop.CandyShop.entity.Candy;
import com.CandyShop.CandyShop.entity.Customer;

import com.CandyShop.CandyShop.entity.Order;
import com.CandyShop.CandyShop.exception.EntityNotFoundException;
import com.CandyShop.CandyShop.repository.CandyRepository;
import com.CandyShop.CandyShop.repository.CustomerRepository;

import com.CandyShop.CandyShop.repository.OrderRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerRepository customerRepository;
	private CandyRepository candyRepository;
	private OrderRepository orderRepository;
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public Customer getCustomer(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		return unwrapCustomer(customer, id);
	}
	
	@Override
	public Customer getCustomer(String name) {
		return customerRepository.findByName(name);
	}
	
	@Override
	public Candy getCandy(Long id) {
		Optional<Candy> candy = candyRepository.findById(id);
		return unwrapCandy(candy, id);
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}

	@Override
	public List<Customer> getCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}

	public Customer unwrapCustomer(Optional<Customer> customer, Long id) {
		if (customer.isPresent()) return customer.get();
		else throw new EntityNotFoundException(Customer.class, id);
	}
	
	public Candy unwrapCandy(Optional<Candy> candy, Long id) {
		if (candy.isPresent()) return candy.get();
		else throw new EntityNotFoundException(Candy.class, id);
	}

	@Override
	public void orderACandy(Long customerId, Long candyId) {
		Order order = new Order(getCustomer(customerId), getCandy(candyId));
		orderRepository.save(order);
	}
	
}

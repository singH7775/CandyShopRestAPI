package com.CandyShop.CandyShop.service;

import java.util.List;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.CandyShop.CandyShop.entity.Customer;
import com.CandyShop.CandyShop.exception.EntityNotFoundException;
import com.CandyShop.CandyShop.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerRepository customerRepository;

	@Override
	public Customer getCustomer(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		return unwrapCustomer(customer, id);
	}

	@Override
	public Customer saveCustomer(Customer customer) {
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
	
}

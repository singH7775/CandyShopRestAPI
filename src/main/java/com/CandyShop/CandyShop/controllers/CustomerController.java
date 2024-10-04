package com.CandyShop.CandyShop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CandyShop.CandyShop.entity.Customer;
import com.CandyShop.CandyShop.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/")
	public ResponseEntity<HttpStatus> testingResponse() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<HttpStatus> saveCustomer(@Valid @RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
		return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Customer>> getCustomers() {
		return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/{customerId}/order/{candyId}")
	public ResponseEntity<HttpStatus> orderIcecream(@PathVariable Long customerId, @PathVariable Long candyId) {
		customerService.orderACandy(customerId, candyId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}

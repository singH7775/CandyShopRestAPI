package com.CandyShop.CandyShop.security.manager;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.CandyShop.CandyShop.entity.Customer;
import com.CandyShop.CandyShop.service.CustomerService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomAuthManager implements AuthenticationManager {
	
	private CustomerService customerService;
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Customer customer = customerService.getCustomer(authentication.getName());
		if (customer == null) throw new EntityNotFoundException();
		if (!passwordEncoder.matches(authentication.getCredentials().toString(), customer.getPassword())) {
			throw new BadCredentialsException("Wrong credentials");
		}
		return new UsernamePasswordAuthenticationToken(authentication.getName(), customer.getPassword());
	}

}

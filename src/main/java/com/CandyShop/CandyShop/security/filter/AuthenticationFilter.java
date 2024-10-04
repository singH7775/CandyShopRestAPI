package com.CandyShop.CandyShop.security.filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.CandyShop.CandyShop.entity.Customer;
import com.CandyShop.CandyShop.security.manager.CustomAuthManager;
import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import com.auth0.jwt.algorithms.Algorithm;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {



	private CustomAuthManager authManager;
	

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		try {
			Customer customer = new ObjectMapper().readValue(request.getInputStream(), Customer.class);
			Authentication auth = new UsernamePasswordAuthenticationToken(customer.getName(), customer.getPassword());
			return authManager.authenticate(auth);
		} catch (IOException e) {
			throw new RuntimeException();
		}
		
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write(failed.getMessage());
		response.getWriter().flush();
	}
	
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String token = JWT.create()
				.withSubject(authResult.getName())
				.withExpiresAt(new Date(System.currentTimeMillis() + 7200000))
				.sign(Algorithm.HMAC512("KOAxXdD4WcoYQaNUURSCj2C84QhxCFJJplk4hbiZwiO+12yFliCkedM9eskxNK2I"));
		response.addHeader("Authorization", "Bearer " + token);
	}
	
	
}

package com.CandyShop.CandyShop.security.filter;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getHeader("Authorization") == null || !request.getHeader("Authorization").startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = request.getHeader("Authorization").replace("Bearer ", "");
		String customer = JWT.require(Algorithm.HMAC512("KOAxXdD4WcoYQaNUURSCj2C84QhxCFJJplk4hbiZwiO+12yFliCkedM9eskxNK2I")).build().verify(token)
		.getSubject();
		
		Authentication auth = new UsernamePasswordAuthenticationToken(customer, null, Arrays.asList());
		SecurityContextHolder.getContext().setAuthentication(auth);
		filterChain.doFilter(request, response);
		
	}

	
}

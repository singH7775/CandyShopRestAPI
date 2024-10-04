package com.CandyShop.CandyShop.security;


import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.CandyShop.CandyShop.security.filter.AuthenticationFilter;
import com.CandyShop.CandyShop.security.filter.ExceptionHandlerFilter;
import com.CandyShop.CandyShop.security.filter.JWTAuthorizationFilter;
import com.CandyShop.CandyShop.security.manager.CustomAuthManager;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
	
	CustomAuthManager authManager;

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(authManager);
		authenticationFilter.setFilterProcessesUrl("/customer/authenticate");
        http
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.disable())
            )
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
            	.requestMatchers(HttpMethod.POST, "/customer/register").permitAll()
            	.requestMatchers("/h2/**").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
            .addFilter(authenticationFilter)
            .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class);

        return http.build();
    }
	
}

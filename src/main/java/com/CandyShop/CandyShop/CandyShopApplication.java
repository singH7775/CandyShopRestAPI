package com.CandyShop.CandyShop;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.CandyShop.CandyShop.entity.Candy;
import com.CandyShop.CandyShop.repository.CandyRepository;


@SpringBootApplication
public class CandyShopApplication implements CommandLineRunner {
	
	@Autowired
	CandyRepository candyRepository;

	public static void main(String[] args) {
		SpringApplication.run(CandyShopApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Candy[] candies = new Candy[] {
				new Candy("Starburst"),
				new Candy("Skittles"),
				new Candy("Sour Patch"),
				new Candy("Jolly Rancher")
		};
		
		for (int i = 0; i < candies.length; i++) {
			candyRepository.save(candies[i]);
		}
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

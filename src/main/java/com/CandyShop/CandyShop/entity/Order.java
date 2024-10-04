package com.CandyShop.CandyShop.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@Nonnull
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
	
	@ManyToOne
	@Nonnull
	@JoinColumn(name = "candy_id", nullable = false)
	private Candy candy;
}

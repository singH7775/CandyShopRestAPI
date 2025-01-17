package com.CandyShop.CandyShop.entity;

import java.util.List;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@NotBlank(message = "Name cannot be blank")
	@Size(min = 3, message = "Name must be longer than 3 characters")
	@Column(name = "name", nullable = false)
	private String name;
	
	@NonNull
	@NotBlank(message = "password cannot be blank")
	@Column(nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Order> orders;
}

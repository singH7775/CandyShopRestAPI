package com.CandyShop.CandyShop.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NonNull
	@NotBlank(message = "Name cannot be blank")
	@Size(min = 3, message = "Name must be longer than 3 characters")
	@Column(name = "name", nullable = false)
	private String name;
	
	@NonNull
	@Min(value = 1, message = "Give this person some cash!")
	@Column(name = "money", nullable = false)
	private Long money;
}

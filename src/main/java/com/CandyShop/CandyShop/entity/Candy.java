package com.CandyShop.CandyShop.entity;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "candy")
public class Candy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@NotBlank(message = "Candy name can not be blank")
	@Size(min = 3, message = "Name must be longer than 3 characters")
	@Column(name = "type", nullable = false)
	private String type;
	
	@OneToMany(mappedBy = "candy", cascade = CascadeType.ALL)
	private List<Order> orders;
}

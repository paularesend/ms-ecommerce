package com.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	@NotBlank
	private String name;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	@NotNull
	@Digits(integer = 6, fraction = 2)
	private Double price;

	@Column(nullable = false)
	@NotNull
	@Max(500)
	private Integer stock;

}

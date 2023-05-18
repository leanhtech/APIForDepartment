package com.ptit.springbootdepartmentstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

	private Integer id;

	private String productDescription;

	private String productName;

	private String status;

	private Double price;

	private String specification;

	private Integer calculationUnit;

	private Integer discount;

	private Integer sold;

	private Integer quantity;

	private BrandDTO brand;

	private CategoryDTO category;
}
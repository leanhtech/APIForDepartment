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

	private String calculationUnit;

	private Integer discount;

	private Integer sold;

	private Integer quantity;
	
	private String image;

	private int brandId;

	private int categoryId;
}
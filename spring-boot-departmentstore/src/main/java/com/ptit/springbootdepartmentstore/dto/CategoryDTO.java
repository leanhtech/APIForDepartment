package com.ptit.springbootdepartmentstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

	private Integer id;

	private String name;

	private String imageUrl;
	
	private byte[] image;
	
	private String note;
}
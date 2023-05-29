package com.ptit.springbootdepartmentstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDTO {

	private int id;

	private String name;
	
	private String imageBase64;
	
	private Integer imageId;

	private String descipttion;
}

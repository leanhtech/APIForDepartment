package com.ptit.springbootdepartmentstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
	private Integer productId;
	private Integer quantity;
	private Float unitPrice;

}
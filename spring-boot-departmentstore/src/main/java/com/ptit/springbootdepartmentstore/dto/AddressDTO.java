package com.ptit.springbootdepartmentstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {

	private Integer id;

	private String addressGeneral;

	private String addressSpecific;

	private int userId;
}
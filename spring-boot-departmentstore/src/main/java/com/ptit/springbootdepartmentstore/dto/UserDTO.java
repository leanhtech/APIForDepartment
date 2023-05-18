package com.ptit.springbootdepartmentstore.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

	private Integer id;

	private String name;

	private String password;

	private String firstName;

	private String lastName;

	private String image;

	private String email;

	private String phone;

	private Date createDate;

	private String status;

	private List<AddressDTO> addressList;

	private PermissionDTO permission;
}
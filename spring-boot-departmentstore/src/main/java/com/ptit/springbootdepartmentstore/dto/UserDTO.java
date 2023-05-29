package com.ptit.springbootdepartmentstore.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private Integer id;

	private String userName;

	private String password;

	private String firstName;

	private String lastName;
	
	private Integer imageId;

	private String email;

	private String phone;

	private Date createDate;

	private String status;

	private List<Integer> addressIds;

	private Integer permissionId;
	
	private String imageBase64;
}
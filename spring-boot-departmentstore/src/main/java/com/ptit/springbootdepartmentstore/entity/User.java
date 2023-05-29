package com.ptit.springbootdepartmentstore.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "user_name", nullable = false)
	private String name;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "created_at")
	private Date createDate;

	@Column(name = "status")
	private String status;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addressList;

	@ManyToOne
	@JoinColumn(name = "permission_id", nullable = false)
	private Permission permission;
	
	@OneToOne
	@JoinColumn(name = "image_id")
    private Image image;
	
	@Column(name = "image_byte")
	@Lob
	private byte[] imageByte;
}
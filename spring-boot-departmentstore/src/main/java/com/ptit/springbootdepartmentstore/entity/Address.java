package com.ptit.springbootdepartmentstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Address {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Integer id;
	
	@Column(name="address_genneral", nullable=false)
	private String addressGeneral;
	
	@Column(name="address_specific", nullable=false)
	private String addressSpecific;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
}

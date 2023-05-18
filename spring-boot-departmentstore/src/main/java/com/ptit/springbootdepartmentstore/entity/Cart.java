package com.ptit.springbootdepartmentstore.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Cart {
	@EmbeddedId
	private CartId id;
	
	@Column(name = "quantity")
	private int quantity;
	
}

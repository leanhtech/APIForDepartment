package com.ptit.springbootdepartmentstore.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;

@SuppressWarnings("serial")
@Embeddable
@Data
@AllArgsConstructor
public class CartId implements Serializable  {
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
}

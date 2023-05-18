package com.ptit.springbootdepartmentstore.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@SuppressWarnings("serial")
@Embeddable
@Data
public class OrderDetailId implements Serializable {

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Orders order;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
}

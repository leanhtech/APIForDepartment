package com.ptit.springbootdepartmentstore.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class OrderDetail {
	@EmbeddedId
    private OrderDetailId id;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="unit_price")
    private Float unitPrice;
}

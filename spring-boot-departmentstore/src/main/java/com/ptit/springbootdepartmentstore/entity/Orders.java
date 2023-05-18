package com.ptit.springbootdepartmentstore.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id", nullable = false)
	private Integer id;

	@Column(name = "order_date")
	private Date date;

	@Column(name = "status")
	private String status;

	@Column(name = "total_price")
	private Integer totalPrice;
	
	@Column(name = "payment_mode")
	private String paymentMode;

	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
    @OneToMany(mappedBy = "id.order")
	private List<OrderDetail> orderDetailList;
}

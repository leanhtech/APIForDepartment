package com.ptit.springbootdepartmentstore.dto;


import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	private Integer id;
	private Date date;
	private String status;
	private Integer totalPrice;
	private String paymentMode;
	private Integer userId;
	private List<OrderDetailDTO> orderDetailList;

}

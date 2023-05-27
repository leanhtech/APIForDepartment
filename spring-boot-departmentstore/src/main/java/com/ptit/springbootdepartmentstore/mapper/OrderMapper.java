package com.ptit.springbootdepartmentstore.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.dto.OrderDTO;
import com.ptit.springbootdepartmentstore.dto.OrderDetailDTO;
import com.ptit.springbootdepartmentstore.entity.OrderDetail;
import com.ptit.springbootdepartmentstore.entity.OrderDetailId;
import com.ptit.springbootdepartmentstore.entity.Orders;
import com.ptit.springbootdepartmentstore.entity.Product;
import com.ptit.springbootdepartmentstore.entity.User;

@Component
public class OrderMapper {

	public OrderDTO toDto(Orders order) {
		OrderDTO ordersDTO = new OrderDTO();
		ordersDTO.setId(order.getId());
		ordersDTO.setDate(order.getDate());
		ordersDTO.setStatus(order.getStatus());
		ordersDTO.setTotalPrice(order.getTotalPrice());
		ordersDTO.setPaymentMode(order.getPaymentMode());
		ordersDTO.setUserId(order.getUser().getId());

		List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
		for (OrderDetail orderDetail : order.getOrderDetailList()) {
			OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
			orderDetailDTO.setProductId(orderDetail.getId().getProduct().getId());
			orderDetailDTO.setQuantity(orderDetail.getQuantity());
			orderDetailDTO.setUnitPrice(orderDetail.getUnitPrice());
			orderDetailDTOList.add(orderDetailDTO);
		}
		ordersDTO.setOrderDetailList(orderDetailDTOList);

		return ordersDTO;
	}

	public Orders toEntity(OrderDTO ordersDTO) {
		Orders order = new Orders();
		order.setId(ordersDTO.getId());
		order.setDate(ordersDTO.getDate());
		order.setStatus(ordersDTO.getStatus());
		order.setTotalPrice(ordersDTO.getTotalPrice());
		order.setPaymentMode(ordersDTO.getPaymentMode());
		User user = new User();
		user.setId(ordersDTO.getUserId());
		order.setUser(user);

		List<OrderDetail> orderDetailList = new ArrayList<>();
		for (OrderDetailDTO orderDetailDTO : ordersDTO.getOrderDetailList()) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setQuantity(orderDetailDTO.getQuantity());
			orderDetail.setUnitPrice(orderDetailDTO.getUnitPrice());
			Product product = new Product();
			product.setId(orderDetailDTO.getProductId());
			OrderDetailId orderDetailId = new OrderDetailId();
			orderDetailId.setOrder(order);
			orderDetailId.setProduct(product);
			orderDetail.setId(orderDetailId);
			orderDetailList.add(orderDetail);
		}
		order.setOrderDetailList(orderDetailList);

		return order;
	}
}
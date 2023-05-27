package com.ptit.springbootdepartmentstore.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.springbootdepartmentstore.dto.OrderDTO;
import com.ptit.springbootdepartmentstore.mapper.OrderMapper;
import com.ptit.springbootdepartmentstore.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService ordersService;

	@Autowired
	private OrderMapper orderMapper;

	@GetMapping
	public ResponseEntity<List<OrderDTO>> getAllOrders() {
		List<OrderDTO> ordersDTOList = ordersService.getAllOrders();
		return ResponseEntity.ok(ordersDTOList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable Integer id) {
		OrderDTO ordersDTO = ordersService.getOrderById(id);
		if (ordersDTO != null) {
			return ResponseEntity.ok(ordersDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO ordersDTO) {
		OrderDTO savedOrderDTO = ordersService.addOrder(ordersDTO);
		if (savedOrderDTO != null) {
			return ResponseEntity.created(URI.create("/api/orders/" + savedOrderDTO.getId())).body(savedOrderDTO);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<OrderDTO> updateOrder(@PathVariable Integer id, @RequestBody OrderDTO ordersDTO) {
		OrderDTO savedOrderDTO = ordersService.updateOrder(id, ordersDTO);
		if (savedOrderDTO != null) {
			return ResponseEntity.ok(savedOrderDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
		boolean deleted = ordersService.deleteOrder(id);
		if (deleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}

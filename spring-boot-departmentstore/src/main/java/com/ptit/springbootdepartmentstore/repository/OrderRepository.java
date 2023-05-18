package com.ptit.springbootdepartmentstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptit.springbootdepartmentstore.entity.Orders;


public interface OrderRepository extends JpaRepository<Orders, Integer> {

	public List<Orders> findByUserId(Integer userId);
}

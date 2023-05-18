package com.ptit.springbootdepartmentstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptit.springbootdepartmentstore.entity.OrderDetail;
import com.ptit.springbootdepartmentstore.entity.OrderDetailId;


public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {


}

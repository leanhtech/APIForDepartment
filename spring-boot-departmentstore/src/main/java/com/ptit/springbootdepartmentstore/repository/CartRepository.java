package com.ptit.springbootdepartmentstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptit.springbootdepartmentstore.entity.Cart;
import com.ptit.springbootdepartmentstore.entity.CartId;

public interface CartRepository extends JpaRepository<Cart, CartId>{
	List<Cart> findByIdUserId (Integer id);
}

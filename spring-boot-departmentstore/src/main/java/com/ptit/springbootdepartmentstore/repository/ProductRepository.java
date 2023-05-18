package com.ptit.springbootdepartmentstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptit.springbootdepartmentstore.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	
}

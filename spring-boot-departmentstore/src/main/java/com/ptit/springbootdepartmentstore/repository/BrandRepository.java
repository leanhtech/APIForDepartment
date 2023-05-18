package com.ptit.springbootdepartmentstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptit.springbootdepartmentstore.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
	public List<Brand> findByName(String name);
}

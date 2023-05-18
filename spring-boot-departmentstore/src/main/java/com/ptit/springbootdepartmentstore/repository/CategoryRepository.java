package com.ptit.springbootdepartmentstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptit.springbootdepartmentstore.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}	

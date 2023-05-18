package com.ptit.springbootdepartmentstore.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.springbootdepartmentstore.dto.CategoryDTO;
import com.ptit.springbootdepartmentstore.dto.ProductDTO;
import com.ptit.springbootdepartmentstore.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public List<CategoryDTO> getCategoryList() {
		return categoryService.getCategoryList();
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable int id) {
		CategoryDTO categoryDTO = categoryService.getCategory(id);
		if (categoryDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoryDTO);
	}

	@PostMapping
	public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO) {
		categoryService.saveCategory(categoryDTO);
		return ResponseEntity.ok(categoryDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable int id, @RequestBody CategoryDTO categoryDTO) {
		if (categoryDTO.getId() != id) {
			return ResponseEntity.badRequest().build();
		}
		CategoryDTO updatedCategoryDTO = categoryService.updateCategory(categoryDTO);
		if (updatedCategoryDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedCategoryDTO);
	}

}
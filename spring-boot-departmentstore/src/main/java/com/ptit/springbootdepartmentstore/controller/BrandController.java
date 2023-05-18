package com.ptit.springbootdepartmentstore.controller;

import java.util.List;

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

import com.ptit.springbootdepartmentstore.dto.BrandDTO;
import com.ptit.springbootdepartmentstore.dto.ProductDTO;
import com.ptit.springbootdepartmentstore.service.BrandService;

@RestController
@RequestMapping("/brands")
@Lazy
public class BrandController {

	@Autowired
	private BrandService brandService;

	@GetMapping
	public List<BrandDTO> getBrandList() {
		return brandService.getBrandList();
	}

	@GetMapping("/{id}")
	public ResponseEntity<BrandDTO> getBrand(@PathVariable int id) {
		BrandDTO brandDTO = brandService.getBrand(id);
		if (brandDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(brandDTO);
	}

	@PostMapping
	public ResponseEntity<BrandDTO> saveBrand(@RequestBody BrandDTO brandDTO) {
		brandService.saveBrand(brandDTO);
		return ResponseEntity.ok(brandDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BrandDTO> updateBrand(@PathVariable int id, @RequestBody BrandDTO brandDTO) {
		if (brandDTO.getId() != id) {
			return ResponseEntity.badRequest().build();
		}
		BrandDTO updatedBrandDTO = brandService.updateBrand(brandDTO);
		if (updatedBrandDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedBrandDTO);
	}
}
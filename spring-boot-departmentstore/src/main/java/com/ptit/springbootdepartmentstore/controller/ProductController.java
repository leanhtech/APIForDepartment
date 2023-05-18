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

import com.ptit.springbootdepartmentstore.dto.ProductDTO;
import com.ptit.springbootdepartmentstore.service.BrandService;
import com.ptit.springbootdepartmentstore.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<ProductDTO> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable int id) {
		ProductDTO productDTO = productService.getProduct(id);
		if (productDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(productDTO);
	}

	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
		ProductDTO savedProductDTO = productService.createProduct(productDTO);
		return ResponseEntity.ok(savedProductDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable int id, @RequestBody ProductDTO productDTO) {
		if (productDTO.getId() != id) {
			return ResponseEntity.badRequest().build();
		}
		ProductDTO updatedProductDTO = productService.updateProduct(productDTO);
		return ResponseEntity.ok(updatedProductDTO);
	}

	@GetMapping("/{id}/productsinbrand")
	public ResponseEntity<List<ProductDTO>> getProductListInBrand(@PathVariable int id) {
		List<ProductDTO> productDTOs = productService.getProductListInBrand(id);
		if (productDTOs == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(productDTOs);
	}

	@GetMapping("/{id}/productsincategory")
	public ResponseEntity<List<ProductDTO>> getProductListInCaterogy(@PathVariable int id) {
		List<ProductDTO> productDTOs = productService.getProductListInCategory(id);
		if (productDTOs == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(productDTOs);
	}
// @DeleteMapping("/{id}")
// public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
// try {
// productService.deleteProduct(id);
// return ResponseEntity.noContent().build();
// } catch (RuntimeException e) {
// return ResponseEntity.status(HttpStatus.CONFLICT).build();
// }
// }
}
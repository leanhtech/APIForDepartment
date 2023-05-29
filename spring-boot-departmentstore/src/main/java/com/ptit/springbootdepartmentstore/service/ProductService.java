package com.ptit.springbootdepartmentstore.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.ProductDTO;
import com.ptit.springbootdepartmentstore.entity.Brand;
import com.ptit.springbootdepartmentstore.entity.Category;
import com.ptit.springbootdepartmentstore.entity.Product;
import com.ptit.springbootdepartmentstore.mapper.ProductMapper;
import com.ptit.springbootdepartmentstore.repository.BrandRepository;
import com.ptit.springbootdepartmentstore.repository.CategoryRepository;
import com.ptit.springbootdepartmentstore.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductMapper productMapper;

	public List<ProductDTO> getAllProducts() {
		return productMapper.toListDTO(productRepository.findAll());
	}

	public ProductDTO getProduct(int id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Product not found"));
		return productMapper.toDTO(product);
	}

	@Transactional(rollbackOn = Exception.class)
	public ProductDTO createProduct(ProductDTO productDTO) {
//		productDTO.setId(null);
//		if (productDTO.getImage() == "") {
//			productDTO.setImage("https://w7.pngwing.com/pngs/470/493/png-transparent-new-product-development-sales-industry-business-new-product-text-label-service.png");
//		}
		
		Product product = productMapper.toEntity(productDTO);
		Product savedProduct = productRepository.save(product);
		return productMapper.toDTO(savedProduct);
	}

	@Transactional(rollbackOn = Exception.class)
	public ProductDTO updateProduct(ProductDTO productDTO) {
		Product product = productRepository.findById(productDTO.getId())
				.orElseThrow(() -> new EntityNotFoundException("Product not found"));
		product = productMapper.toEntity(productDTO);
		Product savedProduct = productRepository.save(product);
		return productMapper.toDTO(savedProduct);
	}

	@Transactional(rollbackOn = Exception.class)
	public void deleteProduct(int id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Product not found"));
		productRepository.delete(product);
	}


	public List<ProductDTO> getProductListInBrand(int id) {
		Brand brand = brandRepository.findById(id)
						.orElseThrow(() -> new EntityNotFoundException("Brand not found"));
		return productMapper.toListDTO(brand.getProductList());
	}

	public List<ProductDTO> getProductListInCategory(Integer categoryId) {
		Category category = categoryRepository.findById(categoryId)
								.orElseThrow(() -> new EntityNotFoundException("Category not found"));
		return productMapper.toListDTO(category.getProductList());
	}
}
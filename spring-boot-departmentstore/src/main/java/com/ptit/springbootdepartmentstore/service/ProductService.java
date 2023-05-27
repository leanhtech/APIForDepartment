package com.ptit.springbootdepartmentstore.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.ProductDTO;
import com.ptit.springbootdepartmentstore.entity.Brand;
import com.ptit.springbootdepartmentstore.entity.Category;
import com.ptit.springbootdepartmentstore.entity.Product;
import com.ptit.springbootdepartmentstore.repository.BrandRepository;
import com.ptit.springbootdepartmentstore.repository.CategoryRepository;
import com.ptit.springbootdepartmentstore.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	BrandService brandService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	BrandRepository brandRepository;

	@Autowired
	CategoryRepository categoryRepository;

	public ProductDTO convertToProductDTO(Product product) {
		return new ProductDTO(product.getId(), product.getProductDescription(), product.getProductName(),
				product.getStatus(), product.getPrice(), product.getSpecification(), product.getCalculationUnit(),
				product.getDiscount(), product.getSold(), product.getQuantity(),
				product.getImage(),
				product.getBrand().getId(),
				product.getCategory().getId());
	}

	public List<ProductDTO> convertToListProductDTO(List<Product> products) {
		return products.stream().map(this::convertToProductDTO).collect(Collectors.toList());
	}

	public Product convertToProduct(ProductDTO productDTO) {
		Product product = new Product();
		product.setProductDescription(productDTO.getProductDescription());
		product.setProductName(productDTO.getProductName());
		product.setStatus(productDTO.getStatus());
		product.setPrice(productDTO.getPrice());
		product.setSpecification(productDTO.getSpecification());
		product.setCalculationUnit(productDTO.getCalculationUnit());
		product.setDiscount(productDTO.getDiscount());
		product.setSold(productDTO.getSold());
		product.setQuantity(productDTO.getQuantity());
		product.setImage(productDTO.getImage());
		product.setBrand(brandRepository.findById(productDTO.getBrandId())
				.orElseThrow(() -> new EntityNotFoundException("Brand not found")));
		product.setCategory(categoryRepository.findById(productDTO.getCategoryId())
				.orElseThrow(() -> new EntityNotFoundException("Category not found")));
		return product;
	}

	public List<ProductDTO> getAllProducts() {
		return convertToListProductDTO(productRepository.findAll());
	}

	public ProductDTO getProduct(int id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Product not found"));
		return convertToProductDTO(product);
	}

	@Transactional(rollbackOn = Exception.class)
	public ProductDTO createProduct(ProductDTO productDTO) {
		productDTO.setId(null);
		if (productDTO.getImage() == "") {
			productDTO.setImage("https://w7.pngwing.com/pngs/470/493/png-transparent-new-product-development-sales-industry-business-new-product-text-label-service.png");
		}
		Product product = convertToProduct(productDTO);
		Product savedProduct = productRepository.save(product);
		return convertToProductDTO(savedProduct);
	}

	@Transactional(rollbackOn = Exception.class)
	public ProductDTO updateProduct(ProductDTO productDTO) {
		Product product = productRepository.findById(productDTO.getId())
				.orElseThrow(() -> new EntityNotFoundException("Product not found"));
		product.setProductDescription(productDTO.getProductDescription());
		product.setProductName(productDTO.getProductName());
		product.setStatus(productDTO.getStatus());
		product.setPrice(productDTO.getPrice());
		product.setSpecification(productDTO.getSpecification());
		product.setCalculationUnit(productDTO.getCalculationUnit());
		product.setDiscount(productDTO.getDiscount());
		product.setSold(productDTO.getSold());
		product.setQuantity(productDTO.getQuantity());
		product.setImage(productDTO.getImage());
		product.setBrand(brandRepository.findById(productDTO.getBrandId())
				.orElseThrow(() -> new EntityNotFoundException("Brand not found")));
		product.setCategory(categoryRepository.findById(productDTO.getCategoryId())
				.orElseThrow(() -> new EntityNotFoundException("Category not found")));
		Product savedProduct = productRepository.save(product);
		return convertToProductDTO(savedProduct);
	}

	@Transactional(rollbackOn = Exception.class)
	public void deleteProduct(int id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Product not found"));
		productRepository.delete(product);
	}


	public List<ProductDTO> getProductListInBrand(int id) {
		Brand brand = brandRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Brand not found"));
		return convertToListProductDTO(brand.getProductList());
	}

	public List<ProductDTO> getProductListInCategory(Integer categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new EntityNotFoundException("Category not found"));
		return convertToListProductDTO(category.getProductList());
	}
}
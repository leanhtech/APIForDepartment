package com.ptit.springbootdepartmentstore.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.dto.ProductDTO;
import com.ptit.springbootdepartmentstore.entity.Image;
import com.ptit.springbootdepartmentstore.entity.Product;
import com.ptit.springbootdepartmentstore.repository.BrandRepository;
import com.ptit.springbootdepartmentstore.repository.CategoryRepository;
import com.ptit.springbootdepartmentstore.repository.ImageRepository;

@Component
public class ProductMapper {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private ImageRepository imageRepository;

	public ProductDTO toDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setProductName(product.getProductName());
		productDTO.setProductDescription(product.getProductDescription());
		productDTO.setStatus(product.getStatus());
		productDTO.setPrice(product.getPrice());
		productDTO.setSpecification(product.getSpecification());
		productDTO.setCalculationUnit(product.getCalculationUnit());
		productDTO.setDiscount(product.getDiscount());
		productDTO.setSold(product.getSold());
		productDTO.setQuantity(product.getQuantity());
		if(product.getImageList() != null) {
			List<Integer> imageIds = new ArrayList<>();
			for(Image image: product.getImageList()) {
				imageIds.add(image.getId());
			}
			productDTO.setImageIds(imageIds);
		}
		if(product.getCategory() != null)
			productDTO.setCategoryId(product.getCategory().getId());
		if(product.getBrand() != null)
			productDTO.setBrandId(product.getBrand().getId());
		if(product.getImageByte() != null)
			productDTO.setImageBase64(ImageRepository.generateImageUrl(product.getImageByte()));
		return productDTO;
	}
	
	public Product toEntity(ProductDTO productDTO) {
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setProductName(productDTO.getProductName());
		product.setProductDescription(productDTO.getProductDescription());
		product.setStatus(productDTO.getStatus());
		product.setPrice(productDTO.getPrice());
		product.setSpecification(productDTO.getSpecification());
		product.setCalculationUnit(productDTO.getCalculationUnit());
		product.setDiscount(productDTO.getDiscount());
		product.setSold(productDTO.getSold());
		product.setQuantity(productDTO.getQuantity());
		if(productDTO.getImageIds() != null)
			product.setImageList(imageRepository.findAllById(productDTO.getImageIds()));
		if(productDTO.getCategoryId() != null)
			product.setCategory(categoryRepository.findById(productDTO.getCategoryId()).orElse(null));
		if(productDTO.getBrandId() != null)
			product.setBrand(brandRepository.findById(productDTO.getBrandId()).orElse(null));
		if(productDTO.getImageBase64() != null)
			product.setImageByte(ImageRepository.decodeImageUrl(productDTO.getImageBase64()));
		return product;
	}
	
	public List<ProductDTO> toListDTO (List<Product> products) {
		return products.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
	
}

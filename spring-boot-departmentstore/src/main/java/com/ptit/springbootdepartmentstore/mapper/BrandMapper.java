package com.ptit.springbootdepartmentstore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.dto.BrandDTO;
import com.ptit.springbootdepartmentstore.entity.Brand;

@Component
public class BrandMapper {
	
	public BrandDTO toDTO(Brand brand) {
		BrandDTO brandDTO = new BrandDTO();
		brandDTO.setId(brand.getId());
		brandDTO.setName(brand.getName());
		brandDTO.setDescipttion(brand.getDescipttion());
		return brandDTO;
	}
	
	public Brand toEntity(BrandDTO brandDTO) {
		Brand brand = new Brand();
		brand.setId(brandDTO.getId());
		brand.setName(brandDTO.getName());
		brand.setDescipttion(brandDTO.getDescipttion());
		return brand;
	}
	
	public List<BrandDTO> toListDTO(List<Brand> brands) {
		return brands.stream().map(this::toDTO).collect(Collectors.toList());
	}

}

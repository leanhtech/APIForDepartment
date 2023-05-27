package com.ptit.springbootdepartmentstore.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.BrandDTO;
import com.ptit.springbootdepartmentstore.entity.Brand;
import com.ptit.springbootdepartmentstore.mapper.BrandMapper;
import com.ptit.springbootdepartmentstore.repository.BrandRepository;

@Service
public class BrandService {

	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private BrandMapper brandMapper;

//	public BrandDTO convertToBrandDTO(Brand brand) {
//		return new BrandDTO(brand.getId(), brand.getName(), brand.getDescipttion());
//	}
//
//	public List<BrandDTO> convertToListBrandDTO(List<Brand> brands) {
//		return brands.stream().map(this::convertToBrandDTO).collect(Collectors.toList());
//	}
//
//	public Brand convertToBrand(BrandDTO brandDTO) {
//		Brand brand = new Brand();
//		brand.setName(brandDTO.getName());
//		brand.setDescipttion(brandDTO.getDescipttion());
//		return brand;
//	}

	public List<BrandDTO> getBrandList() {
		return brandMapper.toListDTO(brandRepository.findAll());
	}

	public BrandDTO getBrand(int id) {
		Brand brand = brandRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Brand not found"));
		return brandMapper.toDTO(brand);
	}

	@Transactional(rollbackOn = Exception.class)
	public void saveBrand(BrandDTO brandDTO) {
		Brand brand = brandMapper.toEntity(brandDTO);
		brandRepository.save(brand);
	}

	@Transactional(rollbackOn = Exception.class)
	public BrandDTO updateBrand(BrandDTO brandDTO) {
		Brand brand = brandRepository.findById(brandDTO.getId())
				.orElseThrow(() -> new EntityNotFoundException("Brand not found"));
		brand.setName(brandDTO.getName());
		brand.setDescipttion(brandDTO.getDescipttion());
		brandRepository.save(brand);
		return brandMapper.toDTO(brand);
	}
}
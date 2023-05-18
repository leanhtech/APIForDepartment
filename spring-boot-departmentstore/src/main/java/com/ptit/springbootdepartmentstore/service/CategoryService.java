package com.ptit.springbootdepartmentstore.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.CategoryDTO;
import com.ptit.springbootdepartmentstore.entity.Category;
import com.ptit.springbootdepartmentstore.repository.CategoryRepository;

@Service
@Lazy
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryDTO convertToCategoryDTO(Category category) {
		return new CategoryDTO(category.getId(), category.getName(), category.getImage());
	}

	public List<CategoryDTO> convertToListCategoryDTO(List<Category> categories) {
		return categories.stream().map(this::convertToCategoryDTO).collect(Collectors.toList());
	}

	public Category convertToCategory(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setName(categoryDTO.getName());
		return category;
	}

	public CategoryDTO getCategory(int id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Category not found"));
		return convertToCategoryDTO(category);
	}

	public List<CategoryDTO> getCategoryList() {
		return convertToListCategoryDTO(categoryRepository.findAll());
	}

	@Transactional(rollbackOn = Exception.class)
	public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
		Category category = convertToCategory(categoryDTO);
		categoryRepository.save(category);
		return categoryDTO;
	}

	@Transactional(rollbackOn = Exception.class)
	public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
		Category category = categoryRepository.findById(categoryDTO.getId())
				.orElseThrow(() -> new EntityNotFoundException("Category not found"));
		category.setName(categoryDTO.getName());
		categoryRepository.save(category);
		return categoryDTO;
	}
}
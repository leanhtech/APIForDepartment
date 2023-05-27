package com.ptit.springbootdepartmentstore.mapper;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.dto.CategoryDTO;
import com.ptit.springbootdepartmentstore.entity.Category;

@Component
public class CategoryMapper {
	
	private String generateAvatarUrl(byte[] avatar) {
		if (avatar == null) {
			return null;
		}
		return "data:image/png;base64," + Base64.getEncoder().encodeToString(avatar);
	}
	
	public CategoryDTO toDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setName(category.getName());
		categoryDTO.setNote(category.getNote());
		categoryDTO.setImage(category.getImage());
		if(category.getImage() != null)
			categoryDTO.setImageUrl(generateAvatarUrl(category.getImage()));
		return categoryDTO;
	} 
	
	public Category toEntity(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setId(categoryDTO.getId());
		category.setName(categoryDTO.getName());
		category.setNote(categoryDTO.getNote());
		category.setImage(category.getImage());
		return category;
	}
	
	public List<CategoryDTO> toListDTO(List<Category> categories) {
		return categories.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}

}

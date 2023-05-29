package com.ptit.springbootdepartmentstore.mapper;

import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.dto.ImageDTO;
import com.ptit.springbootdepartmentstore.entity.Image;
import com.ptit.springbootdepartmentstore.repository.ImageRepository;

@Component
public class ImageMapper {
	
	public ImageDTO toDTO (Image image) {
		
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setId(image.getId());
		imageDTO.setImageBase64(ImageRepository.generateImageUrl(image.getImageByte()));
		return imageDTO;
		
	} 
	
	public Image toEntity(ImageDTO imageDTO) {
		
		Image image = new Image();
		image.setId(imageDTO.getId());
		image.setImageByte(ImageRepository.decodeImageUrl(imageDTO.getImageBase64()));
		return image;
		
	}

}

package com.ptit.springbootdepartmentstore.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.ImageDTO;
import com.ptit.springbootdepartmentstore.entity.Image;
import com.ptit.springbootdepartmentstore.mapper.ImageMapper;
import com.ptit.springbootdepartmentstore.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
    private ImageRepository imageRepository;
	
	@Autowired
    private ImageMapper imageMapper;


	public ImageDTO saveImage(ImageDTO imageDTO) {
        Image entity = imageMapper.toEntity(imageDTO);
        entity = imageRepository.save(entity);
        return imageMapper.toDTO(entity);
    }

    public ImageDTO updateImage(ImageDTO imageDTO) {
        Image entity = imageRepository.findById(imageDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Image with id " + imageDTO.getId() + " not found"));
        entity.setImageByte(ImageRepository.decodeImageUrl(imageDTO.getImageBase64()));
        entity = imageRepository.save(entity);
        return imageMapper.toDTO(entity);
    }

    public void deleteImage(Integer id) {
        imageRepository.deleteById(id);
    }

    public List<ImageDTO> findImagesByIds(List<Integer> ids) {
        return imageRepository.findAllById(ids)
        		.stream()
                .map(imageMapper::toDTO)
                .collect(Collectors.toList());
    }
}

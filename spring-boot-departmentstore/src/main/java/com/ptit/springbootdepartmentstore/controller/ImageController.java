package com.ptit.springbootdepartmentstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.springbootdepartmentstore.dto.ImageDTO;
import com.ptit.springbootdepartmentstore.service.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController {

	@Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<ImageDTO> saveImage(@RequestBody ImageDTO imageDTO) {
        ImageDTO savedImage = imageService.saveImage(imageDTO);
        return ResponseEntity.ok(savedImage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImageDTO> updateImage(@RequestBody ImageDTO imageDTO) {
        ImageDTO updatedImage = imageService.updateImage(imageDTO);
        return ResponseEntity.ok(updatedImage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Integer id) {
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<ImageDTO>> findImagesByIds(@RequestParam List<Integer> ids) {
        List<ImageDTO> images = imageService.findImagesByIds(ids);
        return ResponseEntity.ok(images);
   }
}

package com.ptit.springbootdepartmentstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ptit.springbootdepartmentstore.service.ImageStorageService;

@RestController
@RequestMapping("/api/image")
public class ImageController {
	
	@Autowired
	ImageStorageService imageStorageService;
	
	@PostMapping()
	public String uploadImage(@RequestBody MultipartFile file) {
		return imageStorageService.storeFile(file);
	}
	
	@GetMapping("/{fileName}")
	public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.IMAGE_JPEG);
		try {
			byte[] bytes = imageStorageService.readFileContent(fileName);
			return new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<byte[]>(HttpStatus.NO_CONTENT);
		}
	}
}

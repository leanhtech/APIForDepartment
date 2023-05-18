package com.ptit.springbootdepartmentstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ptit.springbootdepartmentstore.service.ImageStorageService;

@Service
public class ImageStorageService {

	Logger logger = LoggerFactory.getLogger(ImageStorageService.class);
// private final Path storageFolder = Paths.get("D:\Documents\Web\backend\spring-boot-departmentstore\src\main\resources\images");

	private final Path storageFolder = Paths.get(".\\src\\main\\resources\\images");

	public ImageStorageService() {
		try {
			Files.createDirectories(storageFolder);
			logger.info(storageFolder.toString());
		} catch (Exception e) {
			throw new RuntimeException("Can not initialize storage", e);
		}
	}

	private boolean isImageFile(MultipartFile file) {
		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		return Arrays.asList(new String[] { "png", "jpg", "jpeg", "bmp" }).contains(fileExtension.trim().toLowerCase());
	}

	public String storeFile(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new RuntimeException("Failed to store empty file");
			}
			// check is image file
			if (!isImageFile(file)) {
				throw new RuntimeException("You can only upload image file");
			}
			// file must be <= 5mb
			float fileSizeInMegabytes = file.getSize() / 1_000_000.0f;

			if (fileSizeInMegabytes > 5.0f) {
				throw new RuntimeException("File must be <= 5Mb");
			}
			// file must be rename, except file duplicate
			String fileExtexsion = FilenameUtils.getExtension(file.getOriginalFilename());
			String generateFileName = UUID.randomUUID().toString().replace("-", "");
			generateFileName = generateFileName + "." + fileExtexsion;
			Path destinationFilePath = this.storageFolder.resolve(Paths.get(generateFileName)).normalize()
					.toAbsolutePath();
			logger.info(destinationFilePath.getParent().toString());
			logger.info(this.storageFolder.toRealPath().toString());
			if (!destinationFilePath.getParent().equals(this.storageFolder.toRealPath())) {
				throw new RuntimeException("Can not store file outside current directory");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
			}

			return generateFileName;

		} catch (Exception e) {
			throw new RuntimeException("Failed to store file");
		}
	}

	public byte[] readFileContent(String fileName) {
		try {
			Path file = storageFolder.resolve(fileName);
			UrlResource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
				return bytes;
			} else {
				throw new RuntimeException("Could not read file" + fileName);
			}

		} catch (IOException e) {
			throw new RuntimeException("Could not read file" + fileName, e);
		}
	}
}
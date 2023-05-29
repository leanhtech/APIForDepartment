package com.ptit.springbootdepartmentstore.repository;

import java.util.Base64;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptit.springbootdepartmentstore.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

	public static String generateImageUrl(byte[] avatar) {
		if (avatar == null) {
			return null;
		}
		return "data:image/png;base64," + Base64.getEncoder().encodeToString(avatar);
	}

	public static byte[] decodeImageUrl(String dataUrl) throws IllegalArgumentException {
		if (dataUrl == null || !dataUrl.startsWith("data:")) {
			throw new IllegalArgumentException("Invalid data URL");
		}

		int commaIndex = dataUrl.indexOf(',');
		if (commaIndex == -1) {
			throw new IllegalArgumentException("Invalid data URL");
		}

		String mimeType = dataUrl.substring(5, commaIndex);
		if (!mimeType.startsWith("image/")) {
			throw new IllegalArgumentException("Invalid MIME type: " + mimeType);
		}

		String base64Data = dataUrl.substring(commaIndex + 1);
		byte[] imageData = Base64.getDecoder().decode(base64Data);

		return imageData;
	}

}

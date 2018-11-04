package com.finder.shop.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.finder.shop.model.Shop;

@Service
public class ImageService {
	
	private static final String IMAGE_FOLDER = "/img/";
	private static final String UPLOAD_FOLDER = "./src/main/resources/static/img/";
	
	@Autowired
	ShopService shopService;
		
	public void saveImage(MultipartFile image, Shop createdShop) {
		if(image.isEmpty()) {
			return;
		}
		try {
			
			byte[] bytes = image.getBytes();
			String imageName = createFileName(image, createdShop);
			
			saveImageToUploadFolder(imageName, bytes);
			shopService.updateShopImageName(IMAGE_FOLDER + imageName, createdShop);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String createFileName(MultipartFile image, Shop createdShop) {
		String[] parts = image.getOriginalFilename().split("\\.");
		String extension = "." + parts[parts.length-1];
		String imageName = createdShop.getId() + extension;
		
		return imageName;
	}
	
	private void saveImageToUploadFolder(String imageName, byte[] bytes) throws IOException {
			Path path = Paths.get(UPLOAD_FOLDER + imageName);
	    Files.write(path, bytes);
	}
}

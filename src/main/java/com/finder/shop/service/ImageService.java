package com.finder.shop.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.finder.shop.model.Shop;
import com.finder.shop.service.utility.FileWriterService;
import com.finder.shop.service.utility.ImageServiceUtility;

@Service
public class ImageService {
	
	public static final String IMAGE_FOLDER = "/img/";
	
	@Autowired
	ShopService shopService;
	
	@Autowired
	FileWriterService fileWriterService;
	
	@Autowired
	ImageServiceUtility imageServiceUtility;
		
	public void saveImage(MultipartFile image, Shop createdShop) {
		if(image.isEmpty()) {
			return;
		}
			
		String imageName = imageServiceUtility.createFileName(image, createdShop);
			
		fileWriterService.writeImageToUploadFolder(imageName, image);
		shopService.updateShopImageName(IMAGE_FOLDER + imageName, createdShop);
	}
}

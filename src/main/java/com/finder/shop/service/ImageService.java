package com.finder.shop.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
	
	private static final String IMAGE_FOLDER = "/img/";
	private static final String UPLOAD_FOLDER = "./src/main/resources/static/img/";
	
	public String getImage(long id) {
		return IMAGE_FOLDER + id + ".jpg";
	}
	
	public void saveImage(MultipartFile image) {
		if(image.isEmpty()) {
			return;
		}
		try {
			
			byte[] bytes = image.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER + image.getOriginalFilename());
	    Files.write(path, bytes);
	    
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}

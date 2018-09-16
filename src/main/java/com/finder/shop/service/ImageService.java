package com.finder.shop.service;

import org.springframework.stereotype.Service;

@Service
public class ImageService {
	
	public String getImage(long id) {
		return "/img/" + id + ".jpg";
	}
}

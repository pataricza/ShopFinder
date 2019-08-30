package com.finder.shop.service;

import org.springframework.web.multipart.MultipartFile;

import com.finder.shop.model.Shop;

public interface ImageService {

  void saveImage(MultipartFile image, Shop createdShop);
}

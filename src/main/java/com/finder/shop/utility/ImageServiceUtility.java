package com.finder.shop.utility;

import org.springframework.web.multipart.MultipartFile;

import com.finder.shop.model.Shop;

public interface ImageServiceUtility {

  String createFileName(MultipartFile image, Shop createdShop);
}

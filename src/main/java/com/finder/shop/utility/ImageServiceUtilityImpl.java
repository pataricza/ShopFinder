package com.finder.shop.utility;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.finder.shop.model.Shop;

@Service
public class ImageServiceUtilityImpl implements ImageServiceUtility {

  @Override
  public String createFileName(MultipartFile image, Shop createdShop) {
    String[] parts = image.getName().split("\\.");
    String extension = "." + parts[parts.length - 1];
    String imageName = createdShop.getId() + extension;

    return imageName;
  }
}

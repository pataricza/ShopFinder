package com.finder.shop.utility;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.finder.shop.model.Shop;

@Service
public class ImageServiceUtilityImpl implements ImageServiceUtility {

  private static final String EXTENSION = ".jpg";

  @Override
  public String createFileName(MultipartFile image, Shop createdShop) {
    return createdShop.getId() + EXTENSION;
  }
}

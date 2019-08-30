package com.finder.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.finder.shop.model.Shop;
import com.finder.shop.utility.ImageServiceUtilityImpl;

@Service
public class ImageServiceImpl implements ImageService {

  public static final String IMAGE_FOLDER = "/img/";

  private ShopService shopService;

  private FileWriterService fileWriterService;

  private ImageServiceUtilityImpl imageServiceUtility;
  
  @Autowired
  public ImageServiceImpl(ShopService shopService,
                          FileWriterService fileWriterService,
                          ImageServiceUtilityImpl imageServiceUtility) {
    this.shopService = shopService;
    this.fileWriterService = fileWriterService;
    this.imageServiceUtility = imageServiceUtility;
  }

  @Override
  public void saveImage(MultipartFile image, Shop createdShop) {
    if (image.isEmpty()) {
      return;
    }

    String imageName = imageServiceUtility.createFileName(image, createdShop);

    fileWriterService.writeImageToUploadFolder(imageName, image);
    shopService.updateShopImageName(IMAGE_FOLDER + imageName, createdShop);
  }
}

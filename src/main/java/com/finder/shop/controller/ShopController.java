package com.finder.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.finder.shop.model.Shop;
import com.finder.shop.service.ImageService;
import com.finder.shop.service.ShopService;

@CrossOrigin("*")
@RestController
public class ShopController {

  private ShopService shopService;

  private ImageService imageService;
  
  @Autowired
  public ShopController(ShopService shopService, ImageService imageService) {
    this.shopService = shopService;
    this.imageService = imageService;
  }

  @GetMapping("/shop")
  public List<Shop> getAllShops() {
    return shopService.getAllShops();
  }

  @GetMapping(value = "/shop/{id}")
  public Shop getShopById(@PathVariable(name = "id") long id) {
    return shopService.getShopById(id);
  }

  @PostMapping(value = "/shop")
  @ResponseStatus(HttpStatus.CREATED)
  public void addShop(@RequestPart("shop") Shop shop,
                      @RequestPart("image") MultipartFile image) {
    Shop createdShop = shopService.addShop(shop);
    imageService.saveImage(image, createdShop);
  }
}

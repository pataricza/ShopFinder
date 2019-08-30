package com.finder.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.shop.model.Shop;
import com.finder.shop.repository.ShopRepository;

@Service
public class ShopService {

  @Autowired
  private ShopRepository shopRepository;

  public List<Shop> getAllShops() {
    return (List<Shop>) shopRepository.findAll();
  }

  public Shop findShopById(long id) {
    return shopRepository.findById(id).get();
  }

  public Shop createShop(Shop shop) {
    return shopRepository.save(shop);
  }

  public void updateShopImageName(String imageName, Shop shop) {
    shop.setImageName(imageName);
    shopRepository.save(shop);
  }
}

package com.finder.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finder.shop.model.Shop;
import com.finder.shop.repository.ShopRepository;

@Service
public class ShopServiceImpl implements ShopService {

  private ShopRepository shopRepository;
  
  @Autowired
  public ShopServiceImpl(ShopRepository shopRepository) {
    this.shopRepository = shopRepository;
  }

  @Override
  public List<Shop> getAllShops() {
    return (List<Shop>) shopRepository.findAll();
  }

  @Override
  public Shop getShopById(long id) {
    return shopRepository.findById(id).get();
  }

  @Override
  public Shop createShop(Shop shop) {
    return shopRepository.save(shop);
  }

  @Override
  public void updateShopImageName(String imageName, Shop shop) {
    shop.setImageName(imageName);
    shopRepository.save(shop);
  }
}

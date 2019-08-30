package com.finder.shop.service;

import java.util.List;

import com.finder.shop.model.Shop;

public interface ShopService {

  List<Shop> getAllShops();
  Shop findShopById(long id);
  Shop createShop(Shop shop);
  void updateShopImageName(String imageName, Shop shop);
}

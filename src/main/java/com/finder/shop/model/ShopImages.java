package com.finder.shop.model;

import org.springframework.stereotype.Service;

@Service
public class ShopImages {

	 private String shopImage;

	  public ShopImages(){
	    shopImage = "/img/1.jpg";
	  }

	  public String getImage() {
	    return shopImage;
	}
}

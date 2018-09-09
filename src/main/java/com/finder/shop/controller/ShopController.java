package com.finder.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.finder.shop.model.Shop;
import com.finder.shop.service.ShopService;

@Controller
public class ShopController {
	
	@Autowired
	ShopService shopService;

	@GetMapping(value = "/")
	public String shopIndex(Model model) {
		List<Shop> shops = shopService.getAllShops();
		model.addAttribute("shops", shops);
		return "Index";
	}
	
	@GetMapping(value = "/add")
	public String shopAdding() {
		
		return "AddNewShop";
	}
}

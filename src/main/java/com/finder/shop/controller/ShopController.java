package com.finder.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.finder.shop.model.Shop;
import com.finder.shop.service.ImageService;
import com.finder.shop.service.ShopService;

@Controller
public class ShopController {
	
	@Autowired
	ShopService shopService;
	
	@Autowired
	ImageService imageService;

	@GetMapping(value = "/")
	public String shopIndex(Model model) {
		List<Shop> shops = shopService.getAllShops();
		model.addAttribute("shops", shops);
		return "Index";
	}
	
	@GetMapping(value = "/details/{id}")
	public String shopDetails(Model model, @PathVariable(name = "id") long id) {
		Shop shop = shopService.findShopById(id);
		model.addAttribute("shop", shop);
		model.addAttribute("image", imageService.getImage(id));
		return "Details";
	}
	
	@GetMapping(value = "/add")
	public String addShop(Model model) {
		model.addAttribute("shop", new Shop());
		return "AddNewShop";
	}
	
	@PostMapping(value = "/add")
	public String createShop(@ModelAttribute Shop shop, Model model) {
		shopService.createShop(shop);
		return "redirect:/";
	}
}

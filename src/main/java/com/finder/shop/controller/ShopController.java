package com.finder.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {

	@GetMapping(value = "/")
	public String shopIndex() {
		
		return "Index";
	}
	
	@GetMapping(value = "/add")
	public String shopAdding() {
		
		return "AddNewShop";
	}
}

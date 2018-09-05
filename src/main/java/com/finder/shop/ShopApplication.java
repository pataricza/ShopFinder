package com.finder.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.finder.shop.model.Address;
import com.finder.shop.model.Shop;
import com.finder.shop.repository.AddressRepository;
import com.finder.shop.repository.ShopRepository;

@SpringBootApplication
public class ShopApplication implements CommandLineRunner {
	
	@Autowired
	ShopRepository shopRepository;
	
	@Autowired
	AddressRepository adressRepository;

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Address address = new Address();
		address.setCity("Budapest");
		address.setHouseNumber(59);
		address.setStreet("Fiastyúk utca");
		
		Shop shop = new Shop();
		shop.setAddress(address);
		shop.setName("kisbolt");
	//	adressRepository.save(address);
		shopRepository.save(shop);
	}
}

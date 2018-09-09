package com.finder.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.finder.shop.model.Address;
import com.finder.shop.model.OpenHours;
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
		
		OpenHours openHours = new OpenHours();
		openHours.setMonday("08:00-21:00");
		openHours.setTuesday("08:00-21:00");
		openHours.setWednesday("08:00-21:00");
		openHours.setThursday("08:00-21:00");
		openHours.setFriday("08:00-21:00");
		openHours.setSaturday("08:00-15:00");
		openHours.setSunday("08:00-15:00");
		
		Shop shop = new Shop();
		shop.setAddress(address);
		shop.setOpenHours(openHours);
		shop.setName("Kisbolt");
	//	adressRepository.save(address);
		shopRepository.save(shop);
	}
}

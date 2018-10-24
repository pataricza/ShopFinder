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
		
		Address address1 = new Address();
		address1.setCity("Budapest");
		address1.setHouseNumber(88);
		address1.setStreet("Váci utca");
		
		OpenHours openHours1 = new OpenHours();
		openHours1.setMonday("08:00-21:00");
		openHours1.setTuesday("08:00-21:00");
		openHours1.setWednesday("08:00-21:00");
		openHours1.setThursday("08:00-21:00");
		openHours1.setFriday("08:00-21:00");
		openHours1.setSaturday("08:00-15:00");
		openHours1.setSunday("08:00-15:00");
		
		Shop shop1 = new Shop();
		shop1.setAddress(address1);
		shop1.setOpenHours(openHours1);
		shop1.setName("Nagybolt");
	//	adressRepository.save(address);
		shopRepository.save(shop1);
	}
}

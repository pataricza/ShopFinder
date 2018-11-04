package com.finder.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.finder.shop.model.Address;
import com.finder.shop.model.OpenHour;
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
		address.setHouseNumber("59");
		address.setStreet("Fiastyúk utca");
		
		OpenHour test = new OpenHour();
		test.setOpen("08:00");
		test.setClose("21:00");
		
		OpenHours openHours = new OpenHours();
		openHours.setMondayToThursday(test);
		openHours.setFriday(test);
		openHours.setSaturday(test);
		openHours.setSunday(test);
		
		Shop shop = new Shop();
		shop.setAddress(address);
		shop.setOpenHours(openHours);
		shop.setName("Kisbolt");
		shop.setImageName("/img/1.jpg");
	//	adressRepository.save(address);
		shopRepository.save(shop);
		
		Address address1 = new Address();
		address1.setCity("Budapest");
		address1.setHouseNumber("88");
		address1.setStreet("Váci utca");
		
		OpenHour test1 = new OpenHour();
		test1.setOpen("08:00");
		test1.setClose("21:00");
		
		OpenHours openHours1 = new OpenHours();
		openHours1.setMondayToThursday(test1);
		openHours1.setFriday(test1);
		openHours1.setSaturday(test1);
		openHours1.setSunday(test1);
		
		Shop shop1 = new Shop();
		shop1.setAddress(address1);
		shop1.setOpenHours(openHours1);
		shop1.setName("Nagybolt");
	//	adressRepository.save(address);
		shopRepository.save(shop1);
	}
}

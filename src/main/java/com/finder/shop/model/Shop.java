package com.finder.shop.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Shop {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	@OneToOne(cascade = CascadeType.ALL)
	private OpenHours openHours;
	private String imageName;
}

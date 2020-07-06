package com.finder.shop.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @OneToOne(cascade = CascadeType.ALL)
  private Address address;
  @OneToOne(cascade = CascadeType.ALL)
  private OpenHours openHours;
  private String imageName;
}

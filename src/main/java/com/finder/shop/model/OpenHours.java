package com.finder.shop.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OpenHours {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToOne(cascade = CascadeType.ALL)
  private OpenHour mondayToThursday;
  @OneToOne(cascade = CascadeType.ALL)
  private OpenHour friday;
  @OneToOne(cascade = CascadeType.ALL)
  private OpenHour saturday;
  @OneToOne(cascade = CascadeType.ALL)
  private OpenHour sunday;
}

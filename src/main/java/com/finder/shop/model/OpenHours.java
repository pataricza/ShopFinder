package com.finder.shop.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

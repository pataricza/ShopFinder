package com.finder.shop.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.finder.shop.model.Shop;
import com.finder.shop.repository.ShopRepository;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShopServiceImplTest {

  @Mock
  private ShopRepository shopRepository;

  private ShopServiceImpl underTest;
  
  @Before
  public void setUp() {
    underTest = new ShopServiceImpl(shopRepository);
  }

  @Test
  public void getAllShops_Test() {
    // GIVEN
    List<Shop> expected = new ArrayList<>();
    when(shopRepository.findAll()).thenReturn(expected);

    // WHEN
    List<Shop> actual = underTest.getAllShops();

    // THEN
    assertThat(actual).isEqualTo(expected);
    verify(shopRepository, times(1)).findAll();
    verifyNoMoreInteractions(shopRepository);
  }

  @Test
  public void getShopById_Test() {
    // GIVEN
    long id = 1;
    Shop expected = new Shop();
    Optional<Shop> optional = Optional.of(expected);
    when(shopRepository.findById(id)).thenReturn(optional);

    // WHEN
    Shop actual = underTest.getShopById(id);

    // THEN
    assertThat(actual).isEqualTo(expected);
    verify(shopRepository, times(1)).findById(id);
    verifyNoMoreInteractions(shopRepository);
  }

  @Test
  public void createShop_Test() {
    // GIVEN
    Shop expected = new Shop();
    when(shopRepository.save(expected)).thenReturn(expected);

    // WHEN
    Shop actual = underTest.createShop(expected);

    // THEN
    assertThat(actual).isEqualTo(expected);
    verify(shopRepository, times(1)).save(expected);
    verifyNoMoreInteractions(shopRepository);
  }

  @Test
  public void updateShopImageName_Test() {
    // GIVEN
    String imageName = "Image name";
    Shop shop = new Shop();
    shop.setImageName(imageName);

    // WHEN
    underTest.updateShopImageName(imageName, shop);

    // THEN
    verify(shopRepository, times(1)).save(shop);
    verifyNoMoreInteractions(shopRepository);
  }
}

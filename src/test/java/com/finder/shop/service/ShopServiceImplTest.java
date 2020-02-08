package com.finder.shop.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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

  @InjectMocks
  private ShopServiceImpl underTest;

  @Test
  public void getAllShopsTest() {
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
  public void findShopById() {
    // GIVEN
    long id = 1;
    Shop expected = new Shop();
    Optional<Shop> optional = Optional.of(expected);
    when(shopRepository.findById(id)).thenReturn(optional);

    // WHEN
    Shop actual = underTest.findShopById(id);

    // THEN
    assertThat(actual).isEqualTo(expected);
    verify(shopRepository, times(1)).findById(id);
    verifyNoMoreInteractions(shopRepository);
  }

  @Test
  public void createShop() {
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
  public void updateShopImageName() {
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

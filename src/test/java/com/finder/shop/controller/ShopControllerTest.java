package com.finder.shop.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.finder.shop.model.Address;
import com.finder.shop.model.OpenHours;
import com.finder.shop.model.Shop;
import com.finder.shop.service.ImageService;
import com.finder.shop.service.ShopService;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ShopControllerTest {

  @Mock
  private ShopService shopService;

  @Mock
  private ImageService imageService;

  private MockMvc mockMvc;

  @Before
  public void setUp() {
    ShopController underTest = new ShopController(shopService, imageService);
    mockMvc = MockMvcBuilders.standaloneSetup(underTest)
                             .setControllerAdvice(new ErrorController())
                             .build();
  }

  @Test
  public void shopIndexTest() throws Exception {
    // GIVEN
    OpenHours openHours = new OpenHours();
    Address address = new Address();
    String shopName = "The Shop";
    String imageName = "img/1.jpg";

    Shop shop = Shop.builder().id(1L)
                              .name(shopName)
                              .address(address)
                              .openHours(openHours)
                              .imageName(imageName)
                              .build();

    List<Shop> shopList = Collections.singletonList(shop);

    when(shopService.getAllShops()).thenReturn(shopList);

    // WHEN
    ResultActions result = mockMvc.perform(get("/"));

    // THEN  
    result.andExpect(status().isOk())
          .andExpect(view().name("index"))
          .andExpect(model().attribute("shops", hasSize(1)))
          .andExpect(model().attribute("shops", hasItem(
              allOf(hasProperty("id", is(1L)),
                    hasProperty("name", is(shopName)),
                    hasProperty("address", is(address)),
                    hasProperty("openHours", is(openHours)),
                    hasProperty("imageName", is(imageName))
                    )
              )));
    
    verify(shopService, times(1)).getAllShops();
    verifyNoMoreInteractions(shopService);
  }
  
  @Test
  public void shopDetailsTest_shopNotFound() throws Exception {
    // GIVEN
    long id = 1;
    when(shopService.findShopById(id)).thenThrow(NoSuchElementException.class);
    
    // WHEN
    ResultActions result = mockMvc.perform(get("/details/" + id));
    
    // THEN
    result.andExpect(status().isNotFound())
          .andExpect(view().name("error"));
    
    verify(shopService, times(1)).findShopById(id);
    verifyNoMoreInteractions(shopService);
  }
  
  @Test
  public void shopDetailsTest_shopFound() throws Exception {
    // GIVEN
    long id = 1;
    OpenHours openHours = new OpenHours();
    Address address = new Address();
    String shopName = "The Shop";
    String imageName = "img/1.jpg";

    Shop shop = Shop.builder().id(1L)
                              .name(shopName)
                              .address(address)
                              .openHours(openHours)
                              .imageName(imageName)
                              .build();
    
    when(shopService.findShopById(id)).thenReturn(shop);
    
    // WHEN
    ResultActions result = mockMvc.perform(get("/details/" + id));
    
    // THEN
    result.andExpect(status().isOk())
          .andExpect(view().name("details"))
          .andExpect(model().attribute("shop", hasProperty("id", is(id))))
          .andExpect(model().attribute("shop", hasProperty("name", is(shopName))))
          .andExpect(model().attribute("shop", hasProperty("address", is(address))))
          .andExpect(model().attribute("shop", hasProperty("openHours", is(openHours))))
          .andExpect(model().attribute("shop", hasProperty("imageName", is(imageName))));
    
    verify(shopService, times(1)).findShopById(id);
    verifyNoMoreInteractions(shopService);
  }
}

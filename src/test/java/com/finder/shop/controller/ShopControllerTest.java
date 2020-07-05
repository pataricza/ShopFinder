package com.finder.shop.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ShopControllerTest {

  @Mock
  private ShopService shopService;

  @Mock
  private ImageService imageService;

  private MockMvc mockMvc;
  
  private Shop shop;

  @Before
  public void setUp() {
    long id = 1;
    String shopName = "The Shop";
    Address address = new Address();
    OpenHours openHours = new OpenHours();
    String imageName = "img/1.jpg";

    shop = Shop.builder().id(id)
                              .name(shopName)
                              .address(address)
                              .openHours(openHours)
                              .imageName(imageName)
                              .build();
    
    ShopController underTest = new ShopController(shopService, imageService);
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setSuffix(".html");
    mockMvc = MockMvcBuilders.standaloneSetup(underTest)
                             .setControllerAdvice(new ErrorController())
                             .setViewResolvers(viewResolver)
                             .build();
  }

  @Test
  public void getAllShops_Test() throws Exception {
    // GIVEN
    List<Shop> shopList = Collections.singletonList(shop);

    when(shopService.getAllShops()).thenReturn(shopList);

    // WHEN
    ResultActions result = mockMvc.perform(get("/shops"));

    // THEN  
    result.andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(1)))
          .andExpect(jsonPath("$.[0].id", is(1)))
          .andExpect(jsonPath("$.[0].name", is("The Shop")))
          .andExpect(jsonPath("$.[0].address.id", nullValue()))
          .andExpect(jsonPath("$.[0].address.city", nullValue()))
          .andExpect(jsonPath("$.[0].address.street", nullValue()))
          .andExpect(jsonPath("$.[0].address.houseNumber", nullValue()))
          .andExpect(jsonPath("$.[0].openHours.id", nullValue()))
          .andExpect(jsonPath("$.[0].openHours.mondayToThursday", nullValue()))
          .andExpect(jsonPath("$.[0].openHours.friday", nullValue()))
          .andExpect(jsonPath("$.[0].openHours.saturday", nullValue()))
          .andExpect(jsonPath("$.[0].openHours.sunday", nullValue()))
          .andExpect(jsonPath("$.[0].imageName", is("img/1.jpg")));

    verify(shopService, times(1)).getAllShops();
    verifyNoMoreInteractions(shopService);
  }
  
  @Test
  public void shopDetailsTest_shopNotFound() throws Exception {
    // GIVEN
    long id = 1;
    when(shopService.getShopById(id)).thenThrow(NoSuchElementException.class);
    
    // WHEN
    ResultActions result = mockMvc.perform(get("/details/" + id));
    
    // THEN
    result.andExpect(status().isNotFound())
          .andExpect(view().name("error"));
    
    verify(shopService, times(1)).getShopById(1L);
    verifyNoMoreInteractions(shopService);
  }
  
  @Test
  public void shopDetailsTest_shopFound() throws Exception {
    // GIVEN
    long id = 1;
    when(shopService.getShopById(id)).thenReturn(shop);
    
    // WHEN
    ResultActions result = mockMvc.perform(get("/details/" + id));
    
    // THEN
    result.andExpect(status().isOk())
          .andExpect(view().name("details"))
          .andExpect(model().attribute("shop", hasProperty("id", is(1L))))
          .andExpect(model().attribute("shop", hasProperty("name", is("The Shop"))))
          .andExpect(model().attribute("shop", hasProperty("address", is(new Address()))))
          .andExpect(model().attribute("shop", hasProperty("openHours", is(new OpenHours()))))
          .andExpect(model().attribute("shop", hasProperty("imageName", is("img/1.jpg"))));
    
    verify(shopService, times(1)).getShopById(1L);
    verifyNoMoreInteractions(shopService);
  }
  
  @Test
  public void addShopTest() throws Exception {  
    // WHEN
    ResultActions result = mockMvc.perform(get("/add"));
    
    // THEN
    result.andExpect(status().isOk())
          .andExpect(view().name("add"))
          .andExpect(model().attribute("shop", equalTo(new Shop())));
  }
  
  @Test
  public void createShopTest() throws Exception {  
    // GIVEN
    MockMultipartFile image = new MockMultipartFile("image", new byte[100]);
    
    when(shopService.createShop(shop)).thenReturn(shop);
    
    // WHEN
    ResultActions result = mockMvc.perform(multipart("/add")
                                  .file(image)
                                  .param("id", "1")
                                  .param("name", "The Shop")
                                  .param("address.id", "")
                                  .param("openHours.id", "")
                                  .param("imageName", "img/1.jpg"));
        
    // THEN
    result.andExpect(status().is3xxRedirection())
          .andExpect(redirectedUrl("/"));

    verify(shopService, times(1)).createShop(shop);
    verify(imageService, times(1)).saveImage(image, shop);
    verifyNoMoreInteractions(shopService, imageService);
  }
}

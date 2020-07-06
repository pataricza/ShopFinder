package com.finder.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.finder.shop.model.Address;
import com.finder.shop.model.OpenHours;
import com.finder.shop.model.Shop;
import com.finder.shop.service.ImageService;
import com.finder.shop.service.ShopService;

import java.nio.charset.StandardCharsets;
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
    String shopName = "The Shop";
    Address address = new Address();
    OpenHours openHours = new OpenHours();
    String imageName = "img/1.jpg";

    shop = Shop.builder().name(shopName)
                              .address(address)
                              .openHours(openHours)
                              .imageName(imageName)
                              .build();
    
    ShopController underTest = new ShopController(shopService, imageService);
    mockMvc = MockMvcBuilders.standaloneSetup(underTest)
                             .setControllerAdvice(new ErrorController())
                             .build();
  }

  @Test
  public void getAllShops_Test() throws Exception {
    // GIVEN
    List<Shop> shopList = Collections.singletonList(shop);

    when(shopService.getAllShops()).thenReturn(shopList);

    // WHEN
    ResultActions result = mockMvc.perform(get("/shop"));

    // THEN  
    result.andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(1)))
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
  public void getShopById_shopNotFound_Test() throws Exception {
    // GIVEN
    long id = 1;
    when(shopService.getShopById(id)).thenThrow(NoSuchElementException.class);
    
    // WHEN
    ResultActions result = mockMvc.perform(get("/shop/" + id));
    
    // THEN
    result.andExpect(status().isNotFound())
          .andExpect(jsonPath("errorMessage1", is(ErrorController.ErrorMessage.SHOP_NOT_FOUND_1.getMessage())))
          .andExpect(jsonPath("errorMessage2", is(ErrorController.ErrorMessage.SHOP_NOT_FOUND_2.getMessage())));

    verify(shopService, times(1)).getShopById(1L);
    verifyNoMoreInteractions(shopService);
  }
  
  @Test
  public void getShopById_shopFound_Test() throws Exception {
    // GIVEN
    long id = 1;
    when(shopService.getShopById(id)).thenReturn(shop);
    
    // WHEN
    ResultActions result = mockMvc.perform(get("/shop/" + id));
    
    // THEN
    result.andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is("The Shop")))
            .andExpect(jsonPath("$.address.id", nullValue()))
            .andExpect(jsonPath("$.address.city", nullValue()))
            .andExpect(jsonPath("$.address.street", nullValue()))
            .andExpect(jsonPath("$.address.houseNumber", nullValue()))
            .andExpect(jsonPath("$.openHours.id", nullValue()))
            .andExpect(jsonPath("$.openHours.mondayToThursday", nullValue()))
            .andExpect(jsonPath("$.openHours.friday", nullValue()))
            .andExpect(jsonPath("$.openHours.saturday", nullValue()))
            .andExpect(jsonPath("$.openHours.sunday", nullValue()))
            .andExpect(jsonPath("$.imageName", is("img/1.jpg")));
    
    verify(shopService, times(1)).getShopById(1L);
    verifyNoMoreInteractions(shopService);
  }

  @Test
  public void addShop_Test() throws Exception {
    // GIVEN
    MockMultipartFile image = new MockMultipartFile("image", "image.jpg", MediaType.IMAGE_JPEG_VALUE, new byte[100]);

    ObjectMapper objectMapper = new ObjectMapper();
    MockMultipartFile shopJson = new MockMultipartFile("shop", "shop", MediaType.APPLICATION_JSON_VALUE, objectMapper.writeValueAsBytes(shop));

    when(shopService.addShop(shop)).thenReturn(shop);
    
    // WHEN
    ResultActions result = mockMvc.perform(multipart("/shop")
                                  .file(image)
                                  .file(shopJson));
        
    // THEN
    result.andExpect(status().isCreated());

    verify(shopService, times(1)).addShop(shop);
    verify(imageService, times(1)).saveImage(image, shop);
    verifyNoMoreInteractions(shopService, imageService);
  }
}

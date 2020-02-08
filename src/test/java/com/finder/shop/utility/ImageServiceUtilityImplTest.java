package com.finder.shop.utility;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.finder.shop.model.Shop;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class ImageServiceUtilityImplTest {
  
  private ImageServiceUtilityImpl underTest;
  
  @Before
  public void setUp() {
    underTest = new ImageServiceUtilityImpl();
  }

  @Test
  public void createFileNameTest() {
    // GIVEN
    MultipartFile image = new MockMultipartFile("Image.jpg", new byte[100]);
    
    Shop shop = Shop.builder().id(3L).build();
    
    String expected = "3.jpg";
    
    // WHEN
    String actual = underTest.createFileName(image, shop);
    
    // THEN
    assertThat(actual).isEqualTo(expected);
  }

}

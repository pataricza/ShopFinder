package com.finder.shop.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import com.finder.shop.model.Shop;
import com.finder.shop.utility.ImageServiceUtilityImpl;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ImageServiceImplTest {

  @Mock
  private ShopServiceImpl shopService;

  @Mock
  private FileWriterServiceImpl fileWriterService;

  @Mock
  private ImageServiceUtilityImpl imageServiceUtility;

  private ImageServiceImpl underTest;
  
  @Before
  public void setUp() {
    underTest = new ImageServiceImpl(shopService, fileWriterService, imageServiceUtility);
  }

  @Test
  public void saveImage() {
    // GIVEN
    Shop shop = new Shop();

    String originalImageName = "Image.jpg";
    MultipartFile image = new MockMultipartFile(originalImageName, new byte[100]);

    String newImageName = "1.jpg";

    when(imageServiceUtility.createFileName(image, shop)).thenReturn(newImageName);

    // WHEN
    underTest.saveImage(image, shop);

    // THEN
    verify(imageServiceUtility).createFileName(image, shop);
    verify(fileWriterService).writeImageToUploadFolder(newImageName, image);
    verify(shopService).updateShopImageName(ImageServiceImpl.IMAGE_FOLDER + newImageName, shop);
    verifyNoMoreInteractions(imageServiceUtility, fileWriterService, shopService);
  }
}

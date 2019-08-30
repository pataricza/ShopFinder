package com.finder.shop.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import com.finder.shop.model.Shop;
import com.finder.shop.service.utility.FileWriterService;
import com.finder.shop.service.utility.ImageServiceUtility;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ImageServiceTest {

  @Mock
  private ShopService shopService;

  @Mock
  private FileWriterService fileWriterService;

  @Mock
  private ImageServiceUtility imageServiceUtility;

  @InjectMocks
  private ImageService underTest;

  @Test
  public void saveImage_EverythingIsOk() {
    // GIVEN
    Shop shop = new Shop();

    String originalFileName = "Image.jpg";
    byte[] contentInBytes = { 80, 65, 70, 14, 29 };
    MultipartFile image = new MockMultipartFile(originalFileName, contentInBytes);

    String newImageName = "1.jpg";

    when(imageServiceUtility.createFileName(image, shop)).thenReturn(newImageName);

    // WHEN
    underTest.saveImage(image, shop);

    // THEN
    verify(imageServiceUtility).createFileName(image, shop);
    verify(fileWriterService).writeImageToUploadFolder(newImageName, image);
    verify(shopService).updateShopImageName(ImageService.IMAGE_FOLDER + newImageName, shop);
    verifyNoMoreInteractions(imageServiceUtility, fileWriterService, shopService);
  }
}

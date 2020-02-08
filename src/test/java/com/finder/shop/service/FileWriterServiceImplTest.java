package com.finder.shop.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class FileWriterServiceImplTest {
  
  private FileWriterServiceImpl underTest;
  
  @Before
  public void setUp() {
    underTest = new FileWriterServiceImpl();
  }

  @Test
  public void writeImageToUploadFolder() {
    String originalImageName = "Image.jpg";
    MultipartFile image = new MockMultipartFile(originalImageName, new byte[100]);
    
    String newImageName = "3.jpg";
  }

}

//package com.finder.shop.service;
//
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.multipart.MultipartFile;
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(FileWriterServiceImpl.class)
//public class FileWriterServiceImplTest {
//  
//  private FileWriterServiceImpl underTest;
//  
//  @Before
//  public void setUp() {
//    PowerMockito.mockStatic(Paths.class, Files.class);
//    underTest = new FileWriterServiceImpl();
//  }
//
//  @Test
//  public void writeImageToUploadFolder() {
//    // GIVEN
//    MultipartFile image = new MockMultipartFile("Image.jpg", new byte[100]);
//    String imageName = "3.jpg";
//    
//    // WHEN
//    underTest.writeImageToUploadFolder(imageName, image);
//    
//    // THEN
//    PowerMockito.verifyStatic(Paths.class);
//  }
//
//}

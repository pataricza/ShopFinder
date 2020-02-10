package com.finder.shop.service;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FileWriterServiceImplTest {
  
  private FileWriterServiceImpl underTest;
  
  @Before
  public void setUp() {
    underTest = spy(new FileWriterServiceImpl());
  }

  @Test
  public void writeImageToUploadFolder() throws IOException {
    // GIVEN
    byte[] bytes = new byte[100];
    MultipartFile image = new MockMultipartFile("Image.jpg", bytes);
    String imageName = "3.jpg";
    
    Path path = mock(Path.class);
    doReturn(path).when(underTest).getPath(imageName);
    doNothing().when(underTest).writeFile(path, bytes);
    
    // WHEN
    underTest.writeImageToUploadFolder(imageName, image);
    
    // THEN
    verify(underTest).getPath(imageName);
    verify(underTest).writeFile(path, bytes);
    verify(underTest).writeImageToUploadFolder(imageName, image);
    verifyNoMoreInteractions(underTest);
  }
}

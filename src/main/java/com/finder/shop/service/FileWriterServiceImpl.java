package com.finder.shop.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileWriterServiceImpl implements FileWriterService {

  public static final String UPLOAD_FOLDER = "./src/main/resources/static/img/";
  
  @Override
  public void writeImageToUploadFolder(String imageName, MultipartFile image) {
    try {
      byte[] bytes = image.getBytes();
      Path path = Paths.get(UPLOAD_FOLDER + imageName);
      Files.write(path, bytes);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}

package com.finder.shop.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileWriterServiceImpl implements FileWriterService {

  private static final String UPLOAD_FOLDER = "./src/main/resources/static/img/";
  
  @Override
  public void writeImageToUploadFolder(String imageName, MultipartFile image) {
    try {
      byte[] bytes = image.getBytes();
      Path path = getPath(imageName);
      writeFile(path, bytes);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  Path getPath(String imageName) {
    return Paths.get(UPLOAD_FOLDER + imageName);
  }
  
  void writeFile(Path path, byte[] bytes) throws IOException {
    Files.write(path, bytes);
  }
}

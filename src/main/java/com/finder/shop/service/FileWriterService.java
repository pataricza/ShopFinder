package com.finder.shop.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileWriterService {
  
  void writeImageToUploadFolder(String imageName, MultipartFile image);
}

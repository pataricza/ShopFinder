package com.finder.shop.controller;

import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;

@ControllerAdvice
public class ErrorController {

  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String shopNotFound(Model model) {
    model.addAttribute("errorMessage1", ErrorMessage.SHOP_NOT_FOUND_1);
    model.addAttribute("errorMessage2", ErrorMessage.SHOP_NOT_FOUND_2);
    return "error";
  }
  
  @ExceptionHandler(MaxUploadSizeExceededException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String fileSizeLimitExceeded(Model model) {
    model.addAttribute("errorMessage1", ErrorMessage.FILE_SIZE_LIMIT_EXCEEDED_1);
    model.addAttribute("errorMessage2", ErrorMessage.FILE_SIZE_LIMIT_EXCEEDED_2);
    return "error";
  }
  
  private enum ErrorMessage {
    SHOP_NOT_FOUND_1("There is no such shop!"),
    SHOP_NOT_FOUND_2("Maybe you want to add it?"),
    FILE_SIZE_LIMIT_EXCEEDED_1("The picture you have tried to upload is too big."),
    FILE_SIZE_LIMIT_EXCEEDED_2("Try a picture with a size less than 2MB");
    
    private final String message;

    private ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
      return message;
    }    
  }
}

package com.finder.shop.controller;

import java.util.NoSuchElementException;

import com.finder.shop.error.ShopFinderError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;

@RestControllerAdvice
public class ErrorController {

  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ShopFinderError shopNotFound() {
    ShopFinderError shopFinderError = new ShopFinderError();
    shopFinderError.setErrorMessage1(ErrorMessage.SHOP_NOT_FOUND_1.message);
    shopFinderError.setErrorMessage2(ErrorMessage.SHOP_NOT_FOUND_2.message);
    return shopFinderError;
  }
  
  @ExceptionHandler(MaxUploadSizeExceededException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ShopFinderError imageTooLarge(Model model) {
    ShopFinderError shopFinderError = new ShopFinderError();
    shopFinderError.setErrorMessage1(ErrorMessage.FILE_SIZE_LIMIT_EXCEEDED_1.message);
    shopFinderError.setErrorMessage2(ErrorMessage.FILE_SIZE_LIMIT_EXCEEDED_2.message);
    return shopFinderError;
  }
  
  public enum ErrorMessage {
    SHOP_NOT_FOUND_1("There is no such shop!"),
    SHOP_NOT_FOUND_2("Maybe you want to add it?"),
    FILE_SIZE_LIMIT_EXCEEDED_1("The image you have tried to upload is too big."),
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

package com.finder.shop.controller;

import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;

@ControllerAdvice
public class ShopFinderErrorController {

  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handleException(Model model) {
    model.addAttribute("errorMessage1", "There is no such shop!");
    model.addAttribute("errorMessage2", "Maybe you want to add it?");
    return "error";
  }
}

package com.nafisulbari.minecraft.website.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler({Exception.class})
    public String handleException(Exception ex) {
        System.out.println(ex);

        return "error";
    }
}

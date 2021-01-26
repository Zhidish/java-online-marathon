package com.softserve.itacademy.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView deleteHandlerException(Exception exception) {
        ModelAndView modelAndView = new ModelAndView("error", HttpStatus.INTERNAL_SERVER_ERROR);
        modelAndView.addObject("info", exception.getMessage());
        return modelAndView;
    }


}

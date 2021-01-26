package com.softserve.itacademy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(NullEntityReferenceException.class)
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView nullEntityReferenceExceptionHandler(EntityNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }

}

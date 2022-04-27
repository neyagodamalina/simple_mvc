package org.example.web.controllers;

import org.example.exceptions.BookShelfLoginException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@ControllerAdvice
//@Controller
public class ErrorController {
    @GetMapping("/404")
    String NotFoundError(){
        return "errors/404";
    }

    @ExceptionHandler(BookShelfLoginException.class)
    public String handlerError(Model model, BookShelfLoginException exception){
        model.addAttribute("errorMessage" , exception.getMessage());
        return "errors/404";
    }

    @ExceptionHandler(Exception.class)
    public String handlerError(Model model, Exception exception){
        model.addAttribute("errorMessage" , exception.getMessage());
        return "errors/404";
    }
}

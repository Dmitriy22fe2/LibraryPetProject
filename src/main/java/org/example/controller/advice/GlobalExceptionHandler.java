package org.example.controller.advice;

import org.example.exception.BookNotFoundException;
import org.example.exception.PersonNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({PersonNotFoundException.class, BookNotFoundException.class})
    public String handleNotFoundException(RuntimeException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "errors/404";
    }

}

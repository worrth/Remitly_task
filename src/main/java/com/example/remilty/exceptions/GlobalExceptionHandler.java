package com.example.remilty.exceptions;


import com.example.remilty.exceptions.exception.CountryNotFoundException;
import com.example.remilty.exceptions.exception.SwiftCodeNotFoundException;
import com.example.remilty.model.ErrorMessage;
import com.example.remilty.model.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handle404Error(NoHandlerFoundException ex, Model model) {
        model.addAttribute("error", "Page not found");
        return new ResponseEntity<>("notFound", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SwiftCodeNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleSwiftCodeNotFound(SwiftCodeNotFoundException ex) {
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage(), 404), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<ErrorMessage> HandleCountryNotFound(CountryNotFoundException ex) {
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage(), 404), HttpStatus.NOT_FOUND);
    }
}

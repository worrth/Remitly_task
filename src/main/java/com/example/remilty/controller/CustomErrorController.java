package com.example.remilty.controller;

import com.example.remilty.model.ErrorMessage;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public ResponseEntity<ErrorMessage> errorHandler(){
        return new ResponseEntity<>(new ErrorMessage("This endpoint does not exist, sorry.",404), HttpStatus.NOT_FOUND);
    }


}

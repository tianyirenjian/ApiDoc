package com.tianyisoft.apidoc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.util.HashMap;

public class BaseController {
    protected ResponseEntity<? extends Serializable> getErrors(HashMap<String, String> map, BindingResult bindingResult) {
        for (ObjectError error: bindingResult.getAllErrors()) {
            map.put("message", ((FieldError)error).getField() + " " +  error.getDefaultMessage());
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    protected void throw404(String msg) {
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, msg
        );
    }
}

package com.tianyisoft.apidoc.controller;

import com.tianyisoft.apidoc.domain.User;
import com.tianyisoft.apidoc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.util.HashMap;

public class BaseController {
    @Autowired
    protected UserService userService;

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

    protected void throw403() {
        throw new ResponseStatusException(
                HttpStatus.FORBIDDEN, "Forbidden."
        );
    }

    protected User getPrinciple() {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principle instanceof UserDetails) {
            return (User) principle;
        }
        String username = principle.toString();
        return userService.findByName(username);
    }
}

package com.tianyisoft.apidoc.controller;

import com.tianyisoft.apidoc.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthController extends BaseController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/token")
    public String login(@RequestParam("username") final String username, @RequestParam("password") final String password) {
        String token = userService.login(username, password);
        if (StringUtils.isEmpty(token)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Unauthorized."
            );
        }
        return token;
    }
}

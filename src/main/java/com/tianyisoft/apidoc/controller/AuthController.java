package com.tianyisoft.apidoc.controller;

import com.tianyisoft.apidoc.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController extends BaseController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/token")
    public Map<String, String> login(@RequestBody Map<String, String> o) {
        String token = userService.login(o.get("username"), o.get("password"));
        if (StringUtils.isEmpty(token)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Unauthorized."
            );
        }
        o.clear();
        o.put("token", token);
        return o;
    }
}

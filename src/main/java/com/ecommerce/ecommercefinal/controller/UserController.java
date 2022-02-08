package com.ecommerce.ecommercefinal.controller;

import com.ecommerce.ecommercefinal.handler.ApiRestException;
import com.ecommerce.ecommercefinal.model.request.UserRequest;
import com.ecommerce.ecommercefinal.model.response.UserResponse;
import com.ecommerce.ecommercefinal.service.EmailService;
import com.ecommerce.ecommercefinal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EmailService emailService;

    @PostMapping("/login")
    public UserResponse login(@RequestParam String email,@RequestParam String password) throws ApiRestException {
        return userService.login(email,password);
    }
    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest userRequest)throws  ApiRestException{
        emailService.sendEmail();
        return userService.register(userRequest);
    }
}

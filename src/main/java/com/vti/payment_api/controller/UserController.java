package com.vti.payment_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.payment_api.dto.request.UpdateUserRequest;
import com.vti.payment_api.dto.response.UserResponse;
import com.vti.payment_api.service.impl.IUserService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor 
public class UserController {

    private final IUserService userService;
    
    @GetMapping("/me")
    public UserResponse me(Authentication authentication) {
        Long userId = (Long) authentication.getDetails();
        return userService.findById(userId);
    }
    
    @PutMapping("/me")
    public UserResponse putMethodName(Authentication authentication, @RequestBody UpdateUserRequest req) {
        Long userId = (Long) authentication.getDetails();

        return userService.update(userId, req);
    }
    
}

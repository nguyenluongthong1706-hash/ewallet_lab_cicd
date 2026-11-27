package com.vti.payment_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.payment_api.dto.request.LoginRequest;
import com.vti.payment_api.dto.request.RegisterRequest;
import com.vti.payment_api.dto.response.ApiResponse;
import com.vti.payment_api.dto.response.LoginResponse;
import com.vti.payment_api.service.impl.IAuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor 
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@Valid @RequestBody RegisterRequest req){
        authService.register(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Đăng KÝ thành công!"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest req){
        LoginResponse loginResponse = authService.login(req);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Đăng nhập thành công!", loginResponse));
    }
}

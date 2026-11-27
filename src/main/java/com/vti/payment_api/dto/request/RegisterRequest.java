package com.vti.payment_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

public record RegisterRequest(
    @NotBlank(message = "Họ tên không được để trống") String fullName,
    @NotBlank(message = "Email không được để trống") @Email(message = "Email Không hợp lệ") String email,
    @NotBlank(message = "Password không được để trống") 
    @Size(min=6, max=100, message="Password ít nhất 6 ký tụ")
    String password
) {}

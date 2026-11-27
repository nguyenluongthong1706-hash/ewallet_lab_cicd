package com.vti.payment_api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank(message = "Email không được để trống") String email,
    @NotBlank(message = "Password không được để trống") String password
) {}

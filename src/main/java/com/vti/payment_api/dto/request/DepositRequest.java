package com.vti.payment_api.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DepositRequest(
    @NotNull(message = "Số tiền không được để trống")
    @Positive (message = "Số tiền lớn hơn 0")
    @Digits(integer = 10, fraction = 2, message = "Số tiền tối đa 16 chữ số tự nhiên và 2 chữ số thập phân")
    BigDecimal amount
) {}

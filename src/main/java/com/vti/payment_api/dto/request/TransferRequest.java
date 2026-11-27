package com.vti.payment_api.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record TransferRequest(
    @NotBlank(message = "Mã ví người nhận không được để trống")
    String receivedWalletCode,
    @NotNull(message = "Số tiền không được để trống")
    @Positive (message = "Số tiền lớn hơn 0")
    @Digits(integer = 10, fraction = 2, message = "Số tiền tối đa 16 chữ số tự nhiên và 2 chữ số thập phân")
    BigDecimal amount,
    @Size(max = 255, message = "Nội dung tối đa 255 ký tự")
    String note
) {

}

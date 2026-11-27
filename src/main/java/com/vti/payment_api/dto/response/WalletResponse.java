package com.vti.payment_api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.vti.payment_api.entity.Wallet;

public record WalletResponse(
    long id,
    String code,
    BigDecimal balance,
    UserResponse userResponse,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public  static WalletResponse from(Wallet w){
        return new WalletResponse(
            w.getId(), w.getCode(), w.getBalance(),
            UserResponse.from(w.getUser()), 
            w.getCreatedAt(), w.getUpdatedAt()
        );
    }
}

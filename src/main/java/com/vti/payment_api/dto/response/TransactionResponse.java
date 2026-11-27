package com.vti.payment_api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.vti.payment_api.enums.ETransactionType;
import com.vti.payment_api.entity.Transaction;
import com.vti.payment_api.enums.ETransactionStatus;

public record TransactionResponse(
    long id,
    String senderWalletCode,
    String receiverWalletCode,
    BigDecimal amount,
    String note,
    ETransactionType type,
    ETransactionStatus status,
    LocalDateTime createdAt
) {
    public static TransactionResponse from(Transaction t){
        return new TransactionResponse(
            t.getId(), 
            t.getSentWallet() != null ? t.getSentWallet().getCode() : null, 
            t.getReceivedWallet() != null ? t.getReceivedWallet().getCode() : null, 
            t.getAmount(), t.getNote(), 
            t.getType(), t.getStatus(), t.getCreatedAt());
    }
}

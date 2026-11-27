package com.vti.payment_api.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.vti.payment_api.dto.request.TransferRequest;
import com.vti.payment_api.dto.response.TransactionResponse;
import com.vti.payment_api.entity.Wallet;
import com.vti.payment_api.enums.ETransactionStatus;
import com.vti.payment_api.enums.ETransactionType;

public interface ITransactionService {
    public List<TransactionResponse> findMine(Long userId);

    public TransactionResponse createTransaction(
        Wallet sentWallet, Wallet receivedWallet, 
        BigDecimal amount,String note,
        ETransactionType type,ETransactionStatus status);

    public TransactionResponse transfer(Long userId, TransferRequest req);
}


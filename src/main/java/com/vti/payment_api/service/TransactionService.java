package com.vti.payment_api.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.payment_api.dto.request.TransferRequest;
import com.vti.payment_api.dto.response.TransactionResponse;
import com.vti.payment_api.entity.Transaction;
import com.vti.payment_api.entity.Wallet;
import com.vti.payment_api.enums.ETransactionStatus;
import com.vti.payment_api.enums.ETransactionType;
import com.vti.payment_api.enums.ErrorCode;
import com.vti.payment_api.exception.AppException;
import com.vti.payment_api.repository.ITransactionRepository;
import com.vti.payment_api.repository.IWalletRepository;
import com.vti.payment_api.service.impl.ITransactionService;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class TransactionService implements  ITransactionService{
    private final ITransactionRepository transactionRepository;
    private  final IWalletRepository walletRepository;

    @Transactional 
    public TransactionResponse createTransaction(
        Wallet sentWallet, 
        Wallet receivedWallet, 
        BigDecimal amount,
        String note,
        ETransactionType type,
        ETransactionStatus status
    ){
        if (sentWallet == null && receivedWallet == null) {
            throw new AppException(ErrorCode.INVALID_TRANSACTION);
        }

        Transaction transaction = new Transaction();
        transaction.setSentWallet(sentWallet);
        transaction.setReceivedWallet(receivedWallet);
        transaction.setAmount(amount);
        transaction.setNote(note);
        transaction.setType(type);
        transaction.setStatus(status);
        Transaction newTransaction = transactionRepository.save(transaction);
        return TransactionResponse.from(newTransaction);
    }

    public List<TransactionResponse> findMine(Long userId){
        Wallet wallet = walletRepository.findByUserId(userId)
            .orElseThrow(()-> new AppException(ErrorCode.WALLET_NOT_FOUND));
        return transactionRepository
                .findBySentWalletIdOrReceivedWalletIdOrderByCreatedAtDesc(wallet.getId(), wallet.getId())
                .stream().map(TransactionResponse::from).toList();
    }

    @Transactional 
    public TransactionResponse transfer(Long userId, TransferRequest req){
        Wallet sentWallet = walletRepository.findByUserId(userId)
            .orElseThrow(()-> new AppException(ErrorCode.WALLET_NOT_FOUND));

        Wallet receivedWallet = walletRepository.findByCode(req.receivedWalletCode())
            .orElseThrow(()-> new AppException(ErrorCode.WALLET_NOT_FOUND));

        if(sentWallet.getId() == receivedWallet.getId()){
            throw new AppException(ErrorCode.TRANSFER_SAME_WALLET);
        }

        long firstId = Math.min(sentWallet.getId(), receivedWallet.getId());
        long secondId = Math.max(sentWallet.getId(), receivedWallet.getId());

        Wallet lookedFirstWallet = walletRepository.findByIdForUpdate(firstId)
            .orElseThrow(()-> new AppException(ErrorCode.WALLET_NOT_FOUND));

        Wallet lookedSecondWallet = walletRepository.findByIdForUpdate(secondId)
            .orElseThrow(()-> new AppException(ErrorCode.WALLET_NOT_FOUND));

        Wallet lookedSentWallet = firstId == lookedFirstWallet.getId() ? lookedFirstWallet : lookedSecondWallet;
        Wallet lookedReceivedWallet = lookedFirstWallet.equals(lookedSentWallet) ? lookedSecondWallet : lookedFirstWallet;

        if (lookedSentWallet.getBalance().compareTo(req.amount()) < 0) {
            throw new AppException(ErrorCode.WALLET_INSUFFICIENT_BALANCE);
        }

        lookedSentWallet.setBalance(lookedSentWallet.getBalance().subtract(req.amount()));
        lookedReceivedWallet.setBalance(lookedReceivedWallet.getBalance().add(req.amount()));

        walletRepository.save(lookedSentWallet);
        walletRepository.save(lookedReceivedWallet);

        return createTransaction(lookedSentWallet, lookedReceivedWallet, req.amount(), req.note(), ETransactionType.TRANSFER, ETransactionStatus.SUCCESS);
    }
}

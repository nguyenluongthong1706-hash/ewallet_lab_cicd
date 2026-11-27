package com.vti.payment_api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.payment_api.enums.ETransactionStatus;
import com.vti.payment_api.enums.ETransactionType;
import com.vti.payment_api.enums.ErrorCode;
import com.vti.payment_api.dto.request.DepositRequest;
import com.vti.payment_api.dto.response.WalletResponse;
import com.vti.payment_api.entity.User;
import com.vti.payment_api.entity.Wallet;
import com.vti.payment_api.exception.AppException;
import com.vti.payment_api.repository.IWalletRepository;
import com.vti.payment_api.service.impl.ITransactionService;
import com.vti.payment_api.service.impl.IWalletService;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class WalletService implements IWalletService {

    private final IWalletRepository walletRepository;
    private final ITransactionService transactionService;
    
    @Override 
    @Transactional
    public Wallet createDefaultWallet(User user){
        if(walletRepository.existsByUserId(user.getId())){
            throw new AppException(ErrorCode.WALLET_ALREADY_EXISTS);
        }
        if (user == null || user.getId() <= 0) {
            throw new IllegalArgumentException("User không hợp lệ để tạo ví");
        }
        Wallet wallet = new Wallet();
        wallet.setCode(String.format("%010d", user.getId()));
        wallet.setUser(user);
        Wallet newWallet = walletRepository.save(wallet);
        return newWallet;
    }

    @Transactional 
    public WalletResponse deposit(Long userId, DepositRequest req){
        Wallet wallet = walletRepository.findByUserId(userId)
            .orElseThrow(()-> new AppException(ErrorCode.WALLET_NOT_FOUND));
        wallet.setBalance(wallet.getBalance().add(req.amount()));

        transactionService.createTransaction(null, wallet, req.amount(), 
            "Nạp tiền cho tài khoản",
            ETransactionType.DEPOSIT,
            ETransactionStatus.SUCCESS
        );

        return WalletResponse.from(walletRepository.save(wallet));
    }

    public WalletResponse findByUserId(Long UserId){
        Wallet wallet = walletRepository.findByUserId(UserId)
            .orElseThrow(()-> new AppException(ErrorCode.WALLET_NOT_FOUND));
        return  WalletResponse.from(wallet);
    }
}

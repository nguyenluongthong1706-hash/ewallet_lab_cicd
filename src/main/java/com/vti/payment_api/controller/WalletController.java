package com.vti.payment_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.payment_api.dto.request.DepositRequest;
import com.vti.payment_api.dto.response.WalletResponse;
import com.vti.payment_api.service.impl.IWalletService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/wallets")
@RequiredArgsConstructor 
public class WalletController {
    private final IWalletService walletService;

    @PostMapping("/me/deposit")
    public WalletResponse postMethodName(Authentication authentication ,@RequestBody DepositRequest req) {
        
        return walletService.deposit((Long) authentication.getDetails(), req);
    }
    
}

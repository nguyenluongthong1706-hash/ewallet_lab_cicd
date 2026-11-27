package com.vti.payment_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.payment_api.dto.request.TransferRequest;
import com.vti.payment_api.dto.response.TransactionResponse;
import com.vti.payment_api.service.impl.ITransactionService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("api/v1/transaction")
@RequiredArgsConstructor 
public class TransactionController {

    private  final ITransactionService transactionService;

    @PostMapping("/transfer")
    public TransactionResponse transfer(Authentication authentication ,@RequestBody TransferRequest req) {
        
        return transactionService.transfer((Long) authentication.getDetails(), req);
    }

    @GetMapping("/me")
    public List<TransactionResponse> getMine (Authentication authentication) {
        return transactionService.findMine((Long) authentication.getDetails());
    }
    
    
}

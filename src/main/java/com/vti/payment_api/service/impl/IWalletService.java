package com.vti.payment_api.service.impl;

import com.vti.payment_api.dto.request.DepositRequest;
import com.vti.payment_api.dto.response.WalletResponse;
import com.vti.payment_api.entity.User;
import com.vti.payment_api.entity.Wallet;

public interface IWalletService {

    public Wallet createDefaultWallet(User user);

    public WalletResponse deposit(Long userId, DepositRequest req);

    public  WalletResponse findByUserId(Long UserId);
}

package com.vti.payment_api.service.impl;

import com.vti.payment_api.dto.request.LoginRequest;
import com.vti.payment_api.dto.request.RegisterRequest;
import com.vti.payment_api.dto.response.LoginResponse;

public interface IAuthService {
    public void register(RegisterRequest req);

    public LoginResponse login(LoginRequest req);
}

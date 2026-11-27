package com.vti.payment_api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.payment_api.enums.ErrorCode;
import com.vti.payment_api.config.JwtUtil;
import com.vti.payment_api.dto.request.LoginRequest;
import com.vti.payment_api.dto.request.RegisterRequest;
import com.vti.payment_api.dto.response.LoginResponse;
import com.vti.payment_api.entity.User;
import com.vti.payment_api.exception.AppException;
import com.vti.payment_api.repository.IUserRepository;
import com.vti.payment_api.service.impl.IAuthService;
import com.vti.payment_api.service.impl.IWalletService;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AuthService implements IAuthService {

    private final IUserRepository userRepository;
    private final IWalletService walletService;
    private  final PasswordEncoder passwordEncoder;
    private  final  JwtUtil jwtUtil;

    @Override
    @Transactional 
    public void register(RegisterRequest req){
        if(userRepository.existsByEmail(req.email())){
            throw new AppException(ErrorCode.USER_ALREADY_EXISTS);
        }
        User user = new User();
        user.setFullName(req.fullName());
        user.setEmail(req.email());
        user.setPassword(passwordEncoder.encode(req.password()));
        User newUser = userRepository.save(user);
        walletService.createDefaultWallet(newUser);
    }

    @Override 
    public LoginResponse login(LoginRequest req){
        User user = userRepository.findByEmail(req.email())
            .orElseThrow(() -> new AppException(ErrorCode.AUTH_INVALID_CREDENTIALS));
        
        if(!passwordEncoder.matches(req.password(), user.getPassword())){
            throw new AppException(ErrorCode.AUTH_INVALID_CREDENTIALS);
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getId());

        return new LoginResponse(token);
    }
}

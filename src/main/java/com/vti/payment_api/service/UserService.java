package com.vti.payment_api.service;

import org.springframework.stereotype.Service;

import com.vti.payment_api.dto.request.UpdateUserRequest;
import com.vti.payment_api.dto.response.UserResponse;
import com.vti.payment_api.entity.User;
import com.vti.payment_api.enums.ErrorCode;
import com.vti.payment_api.exception.AppException;
import com.vti.payment_api.repository.IUserRepository;
import com.vti.payment_api.service.impl.IUserService;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserResponse findById(Long userId){
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return UserResponse.from(user);
    }
    
    public  UserResponse update(Long userId, UpdateUserRequest req){
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        
        user.setFullName(req.fullName());

        return UserResponse.from(user);
    }

}

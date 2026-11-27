package com.vti.payment_api.service.impl;

import com.vti.payment_api.dto.request.UpdateUserRequest;
import com.vti.payment_api.dto.response.UserResponse;

public interface IUserService {

    public UserResponse findById(Long userId);
    
    public  UserResponse update(Long userId, UpdateUserRequest req);

}

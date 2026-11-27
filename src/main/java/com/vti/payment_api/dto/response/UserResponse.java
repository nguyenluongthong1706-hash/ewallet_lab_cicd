package com.vti.payment_api.dto.response;

import java.time.LocalDateTime;

import com.vti.payment_api.entity.User;

public record UserResponse(long id, String fullName, String email, LocalDateTime createdAt) {
    public static UserResponse from(User u){
        return new UserResponse(u.getId(), u.getFullName(), u.getEmail(), u.getCreatedAt());
    }
}

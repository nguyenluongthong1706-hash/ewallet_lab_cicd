package com.vti.payment_api.exception;

import com.vti.payment_api.enums.ErrorCode;

import lombok.Getter;

@Getter 
public class AppException extends RuntimeException {
    private final ErrorCode errorCode;

    public AppException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}

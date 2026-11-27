package com.vti.payment_api.enums;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter 
public enum ErrorCode {
    VALIDATION_ERROR( "Dữ liệu đầu vào không hợp lệ", HttpStatus.BAD_REQUEST),
    AUTH_INVALID_CREDENTIALS( "Sai email/mật khẩu", HttpStatus.UNAUTHORIZED),
    AUTH_TOKEN_EXPIRED( "Token hết hạn", HttpStatus.UNAUTHORIZED),
    AUTH_TOKEN_INVALID( "Token không hợp lệ", HttpStatus.UNAUTHORIZED),
    FORBIDDEN( "Không có quyền truy cập tài nguyên", HttpStatus.FORBIDDEN),
    USER_NOT_FOUND( "Không tìm thấy người dùng", HttpStatus.NOT_FOUND),
    WALLET_NOT_FOUND( "Không tìm thấy ví", HttpStatus.NOT_FOUND),
    TRANSACTION_NOT_FOUND( "Không tìm thấy giao dịch", HttpStatus.NOT_FOUND),
    INVALID_TRANSACTION("Giao dịch không hợp lệ", HttpStatus.UNPROCESSABLE_CONTENT),
    USER_ALREADY_EXISTS( "Email đã được đăng ký", HttpStatus.CONFLICT),
    WALLET_INSUFFICIENT_BALANCE( "Số dư không đủ", HttpStatus.UNPROCESSABLE_CONTENT),
    WALLET_INACTIVE( "Ví bị khóa/vô hiệu hóa", HttpStatus.UNPROCESSABLE_CONTENT),
    WALLET_ALREADY_EXISTS( "Một tài khoản chỉ có một ví", HttpStatus.CONFLICT),
    TRANSFER_SAME_WALLET( "Chuyển tiền đến chính mình", HttpStatus.FORBIDDEN),
    TRANSFER_LIMIT_EXCEEDED( "Vượt hạn mức giao dịch", HttpStatus.UNPROCESSABLE_CONTENT),
    INTERNAL_SERVER_ERROR( "Lỗi hệ thống", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;

    ErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}

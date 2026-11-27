package com.vti.payment_api.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vti.payment_api.enums.ErrorCode;
import com.vti.payment_api.dto.response.ApiResponse;

@RestControllerAdvice 
public class GlobalExceptionHandler {
    // Bắt các lỗi validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String,List<String>>>> handleValidationException(MethodArgumentNotValidException ex){
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        Map<String,List<String>> errDetail = new HashMap<>();
        for(ObjectError err : allErrors){
            String key;
            if(err instanceof FieldError){
                key = ((FieldError) err).getField();
            }else{
                key = err.getObjectName();
            }

            if(!errDetail.containsKey(key)){
                errDetail.put(key, new ArrayList<>());
            }
            errDetail.get(key).add(err.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error("Dữ liệu đầu vào không hợp lệ", errDetail));
    }

    //HttpMessageNotReadableException  xử lý lỗi khi người dùng nhập sai kiểu dữ liệu cho form

    // ...

    // Bắt lỗi custom exception của hệ thống
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Void>> handleAppException(AppException ex) {
    ErrorCode errorCode = ex.getErrorCode();
    return ResponseEntity
            .status(errorCode.getStatus())
            .body(ApiResponse.error(errorCode.getMessage(), null));
    }
}

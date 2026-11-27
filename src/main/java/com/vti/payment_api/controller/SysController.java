package com.vti.payment_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;


@RestController 
@RequestMapping("api/v1/sys")
public class SysController {

    @GetMapping("/status")
    public String status(){
        String msg = "Service is running - " + LocalDateTime.now().toString();
        return msg;
    }

}

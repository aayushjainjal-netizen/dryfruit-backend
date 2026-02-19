package com.dryfruit.backend.model;

import lombok.Data;

@Data
public class Otp {

    private String email;
    private String otp;
    private long expiry;
}

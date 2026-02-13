package com.dryfruit.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsService {

    @Value("${fast2sms.apiKey}")
    private String apiKey;

    private static final String SMS_URL = "https://www.fast2sms.com/dev/bulkV2";

    public String sendOtpSms(String phone, String otp) {

        try {
            RestTemplate restTemplate = new RestTemplate();

            String url = SMS_URL +
                    "?authorization=" + apiKey +
                    "&route=otp&variables_values=" + otp +
                    "&flash=0&numbers=" + phone;

            return restTemplate.getForObject(url, String.class);

        } catch (Exception e) {
            return "SMS Error: " + e.getMessage();
        }
    }
}

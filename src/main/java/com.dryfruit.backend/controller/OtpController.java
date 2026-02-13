package com.dryfruit.backend.controller;

import com.dryfruit.backend.model.Otp;
import com.dryfruit.backend.repository.OtpRepository;
import com.dryfruit.backend.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
public class OtpController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private OtpRepository otpRepository;

    // =======================
    // SEND OTP
    // =======================
    @PostMapping("/send")
    public String sendOtp(@RequestParam String email,
                          @RequestParam String phone) {

        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);

        // save OTP
        Otp o = new Otp();
        o.setEmail(email);
        o.setOtp(otp);
        o.setExpiry(System.currentTimeMillis() + 5 * 60 * 1000);  // 5 min expiry
        otpRepository.save(o);

        // send OTP SMS
        smsService.sendOtpSms(phone, otp);

        return "OTP sent to " + phone;
    }

    // =======================
    // VERIFY OTP
    // =======================
    @PostMapping("/verify")
    public String verifyOtp(@RequestParam String email,
                            @RequestParam String otp) {

        Otp saved = otpRepository.findByEmail(email);

        if (saved == null) return "OTP not found";

        if (System.currentTimeMillis() > saved.getExpiry())
            return "OTP expired";

        if (!saved.getOtp().equals(otp))
            return "Invalid OTP";

        return "OTP Verified Successfully";
    }
}

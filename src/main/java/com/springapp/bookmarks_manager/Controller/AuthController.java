package com.springapp.bookmarks_manager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springapp.bookmarks_manager.Service.EmailService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String email) {
        String otp = emailService.generateOTP();
        emailService.sendOtpEmail(email, otp);
        // Logic: Save 'otp' in a Database or Redis with an expiration time
        return "OTP sent successfully to " + email;
    }
}
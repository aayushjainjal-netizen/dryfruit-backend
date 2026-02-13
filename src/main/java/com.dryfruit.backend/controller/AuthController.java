package com.dryfruit.backend.controller;

import com.dryfruit.backend.model.User;
import com.dryfruit.backend.model.AuthResponse;
import com.dryfruit.backend.repository.UserRepository;
import com.dryfruit.backend.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private UserRepository repo;
    @Autowired private PasswordEncoder encoder;
    @Autowired private JwtUtil jwt;

    // REGISTER USER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User req) {
        req.setPassword(encoder.encode(req.getPassword()));
        req.setRoles(Arrays.asList("USER")); // default role
        repo.save(req);
        return ResponseEntity.ok("User registered!");
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User request) {

        User db = repo.findByEmail(request.getEmail());
        if (db == null) return ResponseEntity.badRequest().body("User Not Found");

        if (!encoder.matches(request.getPassword(), db.getPassword()))
            return ResponseEntity.badRequest().body("Incorrect password!");

        String token = jwt.generateToken(db.getEmail(), db.getRoles());

        return ResponseEntity.ok(new AuthResponse(token, db.getEmail(), db.getRoles()));
    }
}

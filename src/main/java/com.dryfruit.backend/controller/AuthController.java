package com.dryfruit.backend.controller;

import com.dryfruit.backend.model.AuthResponse;
import com.dryfruit.backend.model.User;
import com.dryfruit.backend.repository.UserRepository;
import com.dryfruit.backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    // =========================
    // REGISTER
    // =========================
    @PostMapping("/register")
    public AuthResponse register(@RequestBody User request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        if (request.getRoles() == null || request.getRoles().isEmpty()) {
            request.setRoles(List.of("USER"));
        }

        User savedUser = userRepository.save(request);

        String token = jwtUtil.generateToken(
                savedUser.getEmail(),
                savedUser.getRoles()
        );

        return new AuthResponse(
                token,
                savedUser.getEmail(),
                savedUser.getRoles()
        );
    }

    // =========================
    // LOGIN
    // =========================
    @PostMapping("/login")
    public AuthResponse login(@RequestBody User request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRoles()
        );

        return new AuthResponse(
                token,
                user.getEmail(),
                user.getRoles()
        );
    }
}

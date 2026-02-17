package com.gms.gms.appsecurity.controller;


import com.gms.gms.appsecurity.config.JwtUtil;
import com.gms.gms.appsecurity.dto.LoginRequest;
import com.gms.gms.appsecurity.dto.SignupRequest;
import com.gms.gms.appsecurity.repository.TokenRepository;
import com.gms.gms.appsecurity.repository.UserRepository;
import com.gms.gms.appsecurity.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenRepository tokenRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest request) {
       return authService.signupUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }


}

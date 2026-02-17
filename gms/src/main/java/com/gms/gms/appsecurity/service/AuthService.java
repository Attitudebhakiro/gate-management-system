package com.gms.gms.appsecurity.service;


import com.gms.gms.appsecurity.config.JwtUtil;
import com.gms.gms.appsecurity.dto.LoginRequest;
import com.gms.gms.appsecurity.dto.SignupRequest;
import com.gms.gms.appsecurity.entity.Token;
import com.gms.gms.appsecurity.entity.TokenType;
import com.gms.gms.appsecurity.entity.User;
import com.gms.gms.appsecurity.repository.TokenRepository;
import com.gms.gms.appsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenRepository tokenRepository;

    public ResponseEntity<?> signupUser(SignupRequest request){
        try{
            Map<String,String> response = new HashMap<>();
            if(repository.existsByUsername(request.getEmail())){
                response.put("code", "200");
                response.put("message", "User already registered");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            User user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setUsername(request.getEmail());
            user.setPassword(encoder.encode(request.getPassword()));
            user.setRole(request.getRole());
            user.setUserStatus(true);
            repository.save(user);

            response.put("code", "200");
            response.put("message", "Registration was successful, thank you");
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }catch (Exception e){
            Map<String,String> ex = new HashMap<>();
            ex.put("code", "400");
            ex.put("message", "Registration failed "+e.getMessage());
            //throw new Exception(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
    }

    public ResponseEntity<?> login(LoginRequest request){
        try{
            Map<String,String> response = new HashMap<>();

            User user = repository.findByUsername(request.getEmail())
                    .orElseThrow();

            if (!encoder.matches(request.getPassword(), user.getPassword())) {
                response.put("code", "401");
                response.put("message", "Incorrect Credentials");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
            saveUserToken(user, token);
            response.put("code", "200");
            response.put("username", user.getUsername());
            response.put("token", token);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception ex){
            Map<String,String> e = new HashMap<>();
            e.put("code", "401");
            e.put("message", "Invalid Login");
//            System.out.println(e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e);
        }
    }


    private void saveUserToken(User user, String jwtToken) {
        Token token = new Token(jwtToken, TokenType.BEARER,false,false,user);
        tokenRepository.save(token);
    }
}

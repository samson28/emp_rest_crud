package com.lundu.empback.controller;

import com.lundu.empback.servicies.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/api/login")
    public ResponseEntity<Map<String,String>> jwtToken(String username, String password){
        if(username.equals("")){
            return new ResponseEntity<>(Map.of("status","error","message","username is required"), HttpStatus.UNAUTHORIZED);
        } else if (password.equals("")) {
            return new ResponseEntity<>(Map.of("status","error","message","password is required"), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(authService.authenticate(username,password),HttpStatus.OK);
    }

    @PostMapping("/api/refresh")
    public ResponseEntity<Map<String,String>> jwtRefreshToken(String refreshToken){
        if(refreshToken.isEmpty()){
            return new ResponseEntity<>(Map.of("status","error","message","Refresh Token is required"), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(authService.refreshAccess(refreshToken),HttpStatus.OK);
    }
}

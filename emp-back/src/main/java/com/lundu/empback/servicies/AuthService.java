package com.lundu.empback.servicies;

import com.lundu.empback.security.TokenGenerator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthService {

    private final JwtDecoder jwtDecoder;

    private final TokenGenerator tokenGenerator;

    private final AuthenticationManager authenticationManager;

    public Map<String,String> authenticate(String username, String password){

        Authentication authentication = null;
        Map<String,String> idToken = new HashMap<>();

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)
            );
        } catch (AuthenticationException e) {
            idToken.put("status","error");
            idToken.put("message",e.getMessage());
            return idToken;
        }

        String jwtAccessToken = tokenGenerator.generateAccessToken(authentication);
        String jwtRefreshToken = tokenGenerator.generateRefreshToken(authentication);
        idToken.put("status","success");
        idToken.put("access_token",jwtAccessToken);
        idToken.put("refresh_token",jwtRefreshToken);
        return idToken;
    }

    public Map<String,String> refreshAccess(String refreshToken){
        Map<String,String> idToken = new HashMap<>();
        Instant instant = Instant.now();
        Jwt decodeJwt = null;
        try {
            decodeJwt = jwtDecoder.decode(refreshToken);
        } catch (JwtException e) {
            idToken.put("status","error");
            idToken.put("message",e.getMessage());
            return idToken;
        }
        String subject = decodeJwt.getSubject();

        String jwtAccessToken = tokenGenerator.refreshAccessToken(subject);
        idToken.put("status","success");
        idToken.put("accessToken",jwtAccessToken);
        return idToken;
    }
}

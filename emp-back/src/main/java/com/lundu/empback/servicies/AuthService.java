package com.lundu.empback.servicies;

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
public class AuthService {
    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private JwtDecoder jwtDecoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

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

        Instant instant = Instant.now();
        String scope = authentication.getAuthorities().stream().map(auth->auth.getAuthority()).collect(Collectors.joining(" "));
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(authentication.getName())
                .issuedAt(instant)
                .expiresAt(instant.plus(1, ChronoUnit.MINUTES))
                .issuer("security-service")
                .claim("scope",scope)
                .build();
        String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        JwtClaimsSet jwtClaimsSetRefresh = JwtClaimsSet.builder()
                .subject(authentication.getName())
                .issuedAt(instant)
                .expiresAt(instant.plus(20, ChronoUnit.HOURS))
                .issuer("security-service")
                .build();
        String jwtRefreshToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
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
        UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        String scope = authorities.stream().map(auth->auth.getAuthority()).collect(Collectors.joining(" "));

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(1, ChronoUnit.MINUTES))
                .issuer("security-service")
                .claim("scope",scope)
                .build();
        String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        idToken.put("status","success");
        idToken.put("accessToken",jwtAccessToken);
        return idToken;
    }
}

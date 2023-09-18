package com.lundu.empback.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class TokenGenerator {

    private final JwtEncoder encoder;

    private final UserDetailsService userDetailsService;

    public String generateAccessToken(Authentication authentication){

        Instant now = Instant.now();

        var scope = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("emp_back")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(30))
                .claim("scope", scope)
                .subject(authentication.getName()).build();

        return encoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    public String generateRefreshToken(Authentication authentication){
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("emp_back")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(900))
                .subject(authentication.getName()).build();

        return encoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    public String refreshAccessToken(String subject){
        Instant now = Instant.now();

        UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        String scope = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("emp_back")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(30))
                .claim("scope", scope)
                .subject(subject).build();

        return encoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();

    }

}

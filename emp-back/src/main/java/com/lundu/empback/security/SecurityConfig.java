package com.lundu.empback.security;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private UserDetailServiceImpl userDetailServiceImpl;
    private PasswordEncoder passwordEncoder;


    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) throws Exception {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder);
        authProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(authProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

      return httpSecurity
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(auth->auth.requestMatchers("/api/login/**","/api/refresh/**").permitAll())
                    .authorizeHttpRequests(auth->auth.requestMatchers("/api-docs/**").permitAll())
                    .authorizeHttpRequests(auth->auth.requestMatchers("/api/user/store").permitAll())
                    .authorizeHttpRequests(auth->auth.anyRequest().authenticated())
                    .userDetailsService(userDetailServiceImpl)
                    .sessionManagement(ses->ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                    .httpBasic(Customizer.withDefaults())
                    .build();

    }
}


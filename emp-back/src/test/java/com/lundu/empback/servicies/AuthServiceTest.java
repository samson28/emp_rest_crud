package com.lundu.empback.servicies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String username;
    private String password;

    @BeforeEach
    void setUp() {
        username = "user";
        password = "123456789";
    }


    @Test
    void authenticate_SuccessfulAuthentication_ReturnsToken() {
        // Appelez la méthode authenticate avec des identifiants valides
        Map<String, String> result = authService.authenticate(username, password);

        // Vérifiez si l'authentification a réussi
        assertNotNull(result);
        assertEquals("success", result.get("status"));
        assertNotNull(result.get("access_token"));
        assertNotNull(result.get("refresh_token"));
    }

    @Test
    void authenticate_InvalidCredentials_ReturnsError() {
        // Appelez la méthode authenticate avec des identifiants invalides
        Map<String, String> result = authService.authenticate(username, "invalidpassword");

        // Vérifiez si l'authentification a échoué et renvoie une erreur
        assertNotNull(result);
        assertEquals("error", result.get("status"));
        assertNotNull(result.get("message"));
    }

    @Test
    void refreshAccess_ValidRefreshToken_ReturnsNewAccessToken() {
        // Obtenez un token d'accès initial en appelant la méthode authenticate
        Map<String, String> authResult = authService.authenticate(username, password);
        String refreshToken = authResult.get("refresh_token");

        // Appelez la méthode refreshAccess avec le token de rafraîchissement
        Map<String, String> result = authService.refreshAccess(refreshToken);

        // Vérifiez si le rafraîchissement de l'accès a réussi et renvoie un nouveau token d'accès
        assertNotNull(result);
        assertEquals("success", result.get("status"));
        assertNotNull(result.get("accessToken"));
    }

    @Test
    void refreshAccess_InvalidRefreshToken_ReturnsError() {
        // Appelez la méthode refreshAccess avec un token de rafraîchissement invalide
        Map<String, String> result = authService.refreshAccess("invalid_token");

        // Vérifiez si le rafraîchissement de l'accès a échoué et renvoie une erreur
        assertNotNull(result);
        assertEquals("error", result.get("status"));
        assertNotNull(result.get("message"));
    }
}
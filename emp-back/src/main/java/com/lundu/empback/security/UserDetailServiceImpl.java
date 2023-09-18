package com.lundu.empback.security;

import com.lundu.empback.entities.AppUser;
import com.lundu.empback.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> appUser = appUserRepository.findByEmail(email);
        if(appUser.isEmpty()){
            throw new UsernameNotFoundException("Utilisateur introuvable");
        }

        return User
                .withUsername(appUser.get().getEmail())
                .password(appUser.get().getPassword())
                .roles(appUser.get().getRoles().name())
                .accountExpired(!appUser.get().isAccountNonExpired())
                .credentialsExpired(!appUser.get().isCredentialsNonExpired())
                .disabled(!appUser.get().isEnabled())
                .accountLocked(!appUser.get().isAccountNonLocked())
                .build();
    }
}

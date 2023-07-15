package com.lundu.empback.security;

import com.lundu.empback.dto.mapper.AppUserDTOMapper;
import com.lundu.empback.dto.response.AppUserResponseDTO;
import com.lundu.empback.entities.AppRole;
import com.lundu.empback.entities.AppUser;
import com.lundu.empback.repositories.AppUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> appUser = appUserRepository.findByEmail(email);

        String[] roles = appUser.get().getRoles().stream().map(AppRole::getRole).toArray(String[]::new);
        return User
                .withUsername(appUser.get().getEmail())
                .password(appUser.get().getPassword())
                .roles(roles).build();
    }
}

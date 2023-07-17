package com.lundu.empback.dto.mapper;

import com.lundu.empback.dto.response.AppUserResponseDTO;
import com.lundu.empback.entities.AppUser;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AppUserDTOMapper implements Function<AppUser, AppUserResponseDTO> {
    @Override
    public AppUserResponseDTO apply(AppUser appUser) {
        return new AppUserResponseDTO(
                appUser.getId(),
                appUser.getName(),
                appUser.getEmail(),
                appUser.getRoles()
        );
    }

}

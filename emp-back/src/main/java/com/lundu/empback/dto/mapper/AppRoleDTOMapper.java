package com.lundu.empback.dto.mapper;

import com.lundu.empback.dto.response.AppRoleResponseDTO;
import com.lundu.empback.entities.AppRole;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AppRoleDTOMapper implements Function<AppRole, AppRoleResponseDTO> {
    @Override
    public AppRoleResponseDTO apply(AppRole appRole) {
        return new AppRoleResponseDTO(appRole.getRole());
    }

}

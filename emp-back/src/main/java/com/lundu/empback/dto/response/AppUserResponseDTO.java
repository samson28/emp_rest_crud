package com.lundu.empback.dto.response;

import com.lundu.empback.entities.AppRole;

import java.util.List;

public record AppUserResponseDTO(
        String id,
        String name,
        String email,
        String password,
        List<AppRole> roles
) {
}

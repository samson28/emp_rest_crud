package com.lundu.empback.dto.response;

import com.lundu.empback.entities.Role;

import java.util.List;

public record AppUserResponseDTO(
        String id,
        String name,
        String email,
        Role roles
) {
}

package com.lundu.empback.dto.request;


import com.lundu.empback.entities.Role;

public record AppUserRequestDTO(
        String name,
        String email,
        String password,
        Role roles
) {
}

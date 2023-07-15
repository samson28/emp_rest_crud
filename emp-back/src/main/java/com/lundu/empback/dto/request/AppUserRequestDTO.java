package com.lundu.empback.dto.request;


import java.util.List;

public record AppUserRequestDTO(
        String name,
        String email,
        String password,
        List<String> roles
) {
}

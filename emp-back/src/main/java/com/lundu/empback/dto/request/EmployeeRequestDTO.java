package com.lundu.empback.dto.request;

public record EmployeeRequestDTO(
        String nom,
        String prenom,
        String sex,
        int age,
        int tel,
        String mail,
        String fonction
) {

}

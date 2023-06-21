package com.lundu.empback.dto.response;


public record EmployeeResponseDTO(
        int id,
        String nom,
        String prenom,
        String sex,
        int age,
        int tel,
        String mail,
        String fonction) {

}

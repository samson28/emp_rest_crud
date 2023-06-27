package com.lundu.empback.dto.mapper;

import com.lundu.empback.dto.response.EmployeeResponseDTO;
import com.lundu.empback.entities.Employee;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EmployeeDTOMapper implements Function<Employee, EmployeeResponseDTO> {
    @Override
    public EmployeeResponseDTO apply(Employee employee) {
        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getNom(),
                employee.getPrenom(),
                employee.getSex(),
                employee.getAge(),
                employee.getTel(),
                employee.getMail(),
                employee.getFonction()
        );
    }

}

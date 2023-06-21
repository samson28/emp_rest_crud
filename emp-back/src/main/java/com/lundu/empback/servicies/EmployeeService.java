package com.lundu.empback.servicies;

import com.lundu.empback.dto.request.EmployeeRequestDTO;
import com.lundu.empback.dto.response.EmployeeResponseDTO;
import com.lundu.empback.entities.Employee;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public interface EmployeeService {
    public void save (EmployeeRequestDTO employeeRequestDTO);

    public List<EmployeeResponseDTO> showAll();

    public EmployeeResponseDTO find(int id);

    public void delete(int id);

    public void update(int id, EmployeeRequestDTO employeeRequestDTO);

    public List<EmployeeResponseDTO> search(String tips);
}

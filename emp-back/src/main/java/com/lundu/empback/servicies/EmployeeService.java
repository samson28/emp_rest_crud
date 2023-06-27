package com.lundu.empback.servicies;

import com.lundu.empback.dto.request.EmployeeRequestDTO;
import com.lundu.empback.dto.response.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {
    public void save (EmployeeRequestDTO employeeRequestDTO);

    public List<EmployeeResponseDTO> showAll();

    public EmployeeResponseDTO find(int id);

    public void delete(int id);

    public EmployeeResponseDTO update(int id, EmployeeRequestDTO employeeRequestDTO);

    public List<EmployeeResponseDTO> search(String tips);
}

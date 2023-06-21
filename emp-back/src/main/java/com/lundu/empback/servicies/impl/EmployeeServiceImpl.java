package com.lundu.empback.servicies.impl;

import com.lundu.empback.dto.request.EmployeeRequestDTO;
import com.lundu.empback.dto.response.EmployeeResponseDTO;
import com.lundu.empback.dto.mapper.EmployeeDTOMapper;
import com.lundu.empback.entities.Employee;
import com.lundu.empback.repositories.EmployeeRepository;
import com.lundu.empback.servicies.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeDTOMapper employeeDTOMapper;

    @Override
    public void save(EmployeeRequestDTO employeeRequestDTO) {
        employeeRepository.save(Employee.fromDTO(employeeRequestDTO));
    }

    public List<EmployeeResponseDTO> showAll(){
        return employeeRepository.findAll()
                .stream()
                .map(employeeDTOMapper)
                .collect(Collectors.toList());
    }

    public EmployeeResponseDTO find(int id){
        return employeeRepository.findById(id)
                .map(employeeDTOMapper)
                .orElseThrow(() -> new EntityNotFoundException("Cet employé est inexistant"));
    }

    public void delete(int id){
        employeeRepository.deleteById(id);
    }

    @Override
    public void update(int id, EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Cet employé est inexistant"));
        employee.update(employeeRequestDTO);
        employeeRepository.save(employee);
    }

    public List<EmployeeResponseDTO> search(String tips){
        return employeeRepository.searchEmployeeByNomOrPrenom(tips)
                .stream()
                .map(employeeDTOMapper)
                .collect(Collectors.toList());
    }
}

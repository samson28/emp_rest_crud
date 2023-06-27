package com.lundu.empback.servicies.impl;

import com.lundu.empback.dto.request.EmployeeRequestDTO;
import com.lundu.empback.dto.response.EmployeeResponseDTO;
import com.lundu.empback.dto.mapper.EmployeeDTOMapper;
import com.lundu.empback.entities.Employee;
import com.lundu.empback.repositories.EmployeeRepository;
import com.lundu.empback.servicies.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeDTOMapper employeeDTOMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeDTOMapper employeeDTOMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeDTOMapper = employeeDTOMapper;
    }

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
        Optional<EmployeeResponseDTO> emp = Optional.ofNullable(employeeRepository.findById(id)
                .map(employeeDTOMapper)
                .orElseThrow(() -> new EntityNotFoundException("Cet employé est inexistant")));
        return emp.get();
    }

    public void delete(int id){
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeResponseDTO update(int id, EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Cet employé est inexistant"));
        employee.update(employeeRequestDTO);
        employeeRepository.save(employee);
        return Stream.of(employee).map(employeeDTOMapper).findFirst().get();
    }

    public List<EmployeeResponseDTO> search(String tips){
        return employeeRepository.searchEmployeeByNomOrPrenom(tips)
                .stream()
                .map(employeeDTOMapper)
                .collect(Collectors.toList());
    }
}

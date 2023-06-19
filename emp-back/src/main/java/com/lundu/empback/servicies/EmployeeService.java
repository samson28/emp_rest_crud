package com.lundu.empback.servicies;

import com.lundu.empback.entities.Employee;
import com.lundu.empback.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public void save (Employee employee){
        employeeRepository.save(employee);
    }

    public List<Employee> showAll(){
        return employeeRepository.findAll();
    }

    public Employee find(int id){
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if(optional.isPresent())
        {
            employee = optional.get();
        }else
        {
            throw new RuntimeException("Cet employé est inexistant");
        }
        return employee;
    }

    public void delete(int id){
        employeeRepository.deleteById(id);
    }

    public List<Employee> search(String tips){
        return employeeRepository.searchEmployeeByNomOrPrenom(tips);
    }
}

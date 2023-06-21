package com.lundu.empback.controller;

import com.lundu.empback.dto.mapper.EmployeeDTOMapper;
import com.lundu.empback.dto.request.EmployeeRequestDTO;
import com.lundu.empback.dto.response.EmployeeResponseDTO;
import com.lundu.empback.entities.Employee;
import com.lundu.empback.servicies.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeDTOMapper employeeDTOMapper;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployee(){
        return new ResponseEntity<>(employeeService.showAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/show/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<EmployeeResponseDTO> getOneEmployee(@PathVariable("id") int id){
        return new ResponseEntity<>(employeeService.find(id), HttpStatus.OK);
    }

    @PostMapping(value = "/store", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<EmployeeResponseDTO> addOneEmployee(@RequestBody EmployeeRequestDTO employee){
        employeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable("id") int id, @RequestBody EmployeeRequestDTO employee){
        employeeService.update(id, employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/search/{tips}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<List<EmployeeResponseDTO>> searchEmployee(@PathVariable("tips") String tips){
        return new ResponseEntity<>(employeeService.search(tips), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") int id){
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

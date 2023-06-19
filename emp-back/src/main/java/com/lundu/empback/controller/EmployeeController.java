package com.lundu.empback.controller;

import com.lundu.empback.entities.Employee;
import com.lundu.empback.servicies.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employees = employeeService.showAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(value = "/show/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<Employee> getOneEmployee(@PathVariable("id") int id){
        Employee employee = employeeService.find(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping(value = "/store", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<Employee> addOneEmployee(@RequestBody Employee employee){
        employeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        employeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/search/{tips}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<List<Employee>> searchEmployee(@PathVariable("tips") String tips){
        List<Employee> employees = employeeService.search(tips);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") int id){
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

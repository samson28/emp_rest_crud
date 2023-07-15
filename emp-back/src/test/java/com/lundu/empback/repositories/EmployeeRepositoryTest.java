package com.lundu.empback.repositories;

import com.lundu.empback.entities.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    private Employee Mariam = new Employee("Mariam","Jamel","F",25,70854628,"Mariam.jamel@gmail.com","DG");
    private Employee Alex = new Employee("Alex","Alex","M",25,70986542,"Alex.jamel@gmail.com", "PDG");

    @BeforeEach
    void setUp() {
        employeeRepository.saveAll(
                List.of(Mariam,Alex)
        );
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void searchEmployeeByNomOrPrenom() {

        List<Employee> employees = employeeRepository.searchEmployeeByNomOrPrenom("M");

        assertEquals(Stream.of(Mariam).toList(),employees);
    }
}
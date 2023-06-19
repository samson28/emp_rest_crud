package com.lundu.empback.repositories;

import com.lundu.empback.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e WHERE e.nom LIKE %?1% OR e.prenom LIKE %?1%")
    List<Employee> searchEmployeeByNomOrPrenom(String tips);
}
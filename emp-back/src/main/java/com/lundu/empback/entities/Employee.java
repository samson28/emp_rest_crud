package com.lundu.empback.entities;

import com.lundu.empback.dto.request.EmployeeRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String sex;
    private int age;
    private int tel;
    private String mail;
    private String fonction;

    public static Employee fromDTO(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = new Employee();
        employee.setNom(employeeRequestDTO.nom());
        employee.setSex(employeeRequestDTO.sex());
        employee.setAge(employee.getAge());
        employee.setTel(employeeRequestDTO.tel());
        employee.setMail(employeeRequestDTO.mail());
        employee.setFonction(employeeRequestDTO.fonction());
        return employee;
    }

    public void update(EmployeeRequestDTO employeeRequestDTO) {
        if(employeeRequestDTO.nom() != null) this.nom = employeeRequestDTO.nom();
        if(employeeRequestDTO.prenom() != null) this.prenom = employeeRequestDTO.prenom();
        if(employeeRequestDTO.sex() != null) this.sex = employeeRequestDTO.sex();
        if(employeeRequestDTO.age() != 0) this.age = employeeRequestDTO.age();
        if(employeeRequestDTO.tel() != 0) this.tel = employeeRequestDTO.tel();
        if(employeeRequestDTO.mail() != null) this.mail = employeeRequestDTO.mail();
        if(employeeRequestDTO.fonction() != null) this.fonction = employeeRequestDTO.fonction();
    }
}
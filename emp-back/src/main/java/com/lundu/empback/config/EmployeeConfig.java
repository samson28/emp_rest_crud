package com.lundu.empback.config;

import com.lundu.empback.entities.Employee;
import com.lundu.empback.repositories.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EmployeeConfig {
    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository){
        return args -> {
            Employee Mariam = new Employee(0,"Mariam","Jamel","F",25,70854628,"Mariam.jamel@gmail.com","DG");
            Employee Alex = new Employee(0,"Alex","Alex","M",25,70986542,"Alex.jamel@gmail.com", "PDG");
            repository.saveAll(
                    List.of(Mariam,Alex)
            );
        };
    }
}

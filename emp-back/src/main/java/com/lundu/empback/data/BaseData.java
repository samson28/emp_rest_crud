package com.lundu.empback.data;

import com.lundu.empback.entities.Role;
import com.lundu.empback.entities.AppUser;
import com.lundu.empback.entities.Employee;
import com.lundu.empback.repositories.AppUserRepository;
import com.lundu.empback.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@Configuration
@AllArgsConstructor
public class BaseData {

    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository,AppUserRepository appUserRepository){
        return args -> {
            Employee Mariam = new Employee(0,"Mariam","Jamel","F",25,70854628,"Mariam.jamel@gmail.com","DG");
            Employee Alex = new Employee(0,"Alex","Alex","M",25,70986542,"Alex.jamel@gmail.com", "PDG");
            repository.saveAll(
                    List.of(Mariam,Alex)
            );

            AppUser user1 =  new AppUser(UUID.randomUUID().toString(),"John","john@gmail.com",passwordEncoder.encode("123456789"), Role.USER);
            AppUser user2 = new AppUser(UUID.randomUUID().toString(),"Ama","ama@gmail.com",passwordEncoder.encode("123456789"), Role.USER);

            appUserRepository.saveAll(List.of(user1,user2));
        };
    }
}

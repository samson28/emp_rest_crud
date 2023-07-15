package com.lundu.empback.config;

import com.lundu.empback.entities.AppRole;
import com.lundu.empback.entities.AppUser;
import com.lundu.empback.entities.Employee;
import com.lundu.empback.repositories.AppRoleRepository;
import com.lundu.empback.repositories.AppUserRepository;
import com.lundu.empback.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@Configuration
public class AccountConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository, AppRoleRepository appRoleRepository, AppUserRepository appUserRepository){
        return args -> {
            Employee Mariam = new Employee(0,"Mariam","Jamel","F",25,70854628,"Mariam.jamel@gmail.com","DG");
            Employee Alex = new Employee(0,"Alex","Alex","M",25,70986542,"Alex.jamel@gmail.com", "PDG");
            repository.saveAll(
                    List.of(Mariam,Alex)
            );

            AppRole admin = new AppRole("ADMIN");
            AppRole user = new AppRole("USER");

            appRoleRepository.saveAll(List.of(admin,user));

            AppUser user1 =  new AppUser(UUID.randomUUID().toString(),"John","john@gmail.com",passwordEncoder.encode("123456789"),List.of(user));
            AppUser user2 = new AppUser(UUID.randomUUID().toString(),"Ama","ama@gmail.com",passwordEncoder.encode("123456789"),List.of(user,admin));

            appUserRepository.saveAll(List.of(user1,user2));
        };
    }
}

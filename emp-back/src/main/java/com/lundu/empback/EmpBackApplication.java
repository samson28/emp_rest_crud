package com.lundu.empback;

import com.lundu.empback.entities.AppRole;
import com.lundu.empback.entities.AppUser;
import com.lundu.empback.entities.Employee;
import com.lundu.empback.repositories.AppRoleRepository;
import com.lundu.empback.repositories.AppUserRepository;
import com.lundu.empback.repositories.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
//@EnableConfigurationProperties(RsaKeysConfig.class)
public class EmpBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpBackApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


}

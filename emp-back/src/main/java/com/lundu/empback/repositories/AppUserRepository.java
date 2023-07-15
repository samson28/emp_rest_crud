package com.lundu.empback.repositories;

import com.lundu.empback.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    Optional<AppUser>  findByEmail(String email);
}

package com.lundu.empback.servicies.impl;

import com.lundu.empback.dto.mapper.AppUserDTOMapper;
import com.lundu.empback.dto.request.AppUserRequestDTO;
import com.lundu.empback.dto.response.AppUserResponseDTO;
import com.lundu.empback.entities.AppUser;
import com.lundu.empback.repositories.AppUserRepository;
import com.lundu.empback.servicies.AppUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserDTOMapper appUserDTOMapper;

    @Override
    public void save(AppUserRequestDTO appUserRequestDTO) {
        appUserRepository.save(AppUser.fromDTO(appUserRequestDTO));
    }

    @Override
    public List<AppUserResponseDTO> showAll() {
        return appUserRepository.findAll().stream().map(appUserDTOMapper).collect(Collectors.toList());
    }

    @Override
    public AppUserResponseDTO find(String email) {
        Optional<AppUserResponseDTO> emp = Optional.ofNullable(appUserRepository.findByEmail(email)
                .map(appUserDTOMapper)
                .orElseThrow(() -> new EntityNotFoundException("Cet employé est inexistant")));
        return emp.get();
    }

    @Override
    public void delete(String id) {
        appUserRepository.deleteById(id);
    }

    @Override
    public AppUserResponseDTO update(String id, AppUserRequestDTO appUserRequestDTO) {
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Cet employé est inexistant"));
        appUser.update(appUserRequestDTO);
        appUserRepository.save(appUser);
        return Stream.of(appUser).map(appUserDTOMapper).findFirst().get();
    }

}

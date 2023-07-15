package com.lundu.empback.servicies.impl;

import com.lundu.empback.dto.mapper.AppRoleDTOMapper;
import com.lundu.empback.dto.request.AppRoleRequestDTO;
import com.lundu.empback.dto.response.AppRoleResponseDTO;
import com.lundu.empback.entities.AppRole;
import com.lundu.empback.repositories.AppRoleRepository;
import com.lundu.empback.servicies.AppRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AppRoleServiceImpl implements AppRoleService {

    private final AppRoleRepository appRoleRepository;
    private final AppRoleDTOMapper appRoleDTOMapper;

    @Override
    public void addRole(AppRoleRequestDTO appRoleRequestDTO) {
       appRoleRepository.save(AppRole.fromDTO(appRoleRequestDTO));
    }

    @Override
    public List<AppRoleResponseDTO> showAllRoles() {
        return appRoleRepository.findAll().stream().map(appRoleDTOMapper).collect(Collectors.toList());
    }

    @Override
    public void deleteRole(String id) {
        appRoleRepository.deleteById(id);
    }
}

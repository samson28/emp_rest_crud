package com.lundu.empback.servicies;

import com.lundu.empback.dto.request.AppRoleRequestDTO;
import com.lundu.empback.dto.request.AppUserRequestDTO;
import com.lundu.empback.dto.response.AppRoleResponseDTO;
import com.lundu.empback.dto.response.AppUserResponseDTO;

import java.util.List;

public interface AppRoleService {
    public void addRole (AppRoleRequestDTO appRoleRequestDTO);

    public List<AppRoleResponseDTO> showAllRoles();

    public void deleteRole(String id);
}

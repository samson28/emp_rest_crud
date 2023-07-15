package com.lundu.empback.servicies;

import com.lundu.empback.dto.request.AppUserRequestDTO;
import com.lundu.empback.dto.response.AppUserResponseDTO;

import java.util.List;

public interface AppUserService {
    public void save (AppUserRequestDTO appUserRequestDTO);

    public List<AppUserResponseDTO> showAll();

    public AppUserResponseDTO find(String email);

    public void delete(String id);

    public AppUserResponseDTO update(String id, AppUserRequestDTO appUserRequestDTO);
}

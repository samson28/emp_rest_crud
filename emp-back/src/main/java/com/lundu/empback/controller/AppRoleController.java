package com.lundu.empback.controller;


import com.lundu.empback.dto.request.AppRoleRequestDTO;
import com.lundu.empback.dto.response.AppRoleResponseDTO;
import com.lundu.empback.entities.AppRole;
import com.lundu.empback.servicies.AppRoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/role")
public class AppRoleController {

    private final AppRoleService appRoleService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppRoleResponseDTO>> getAllRole(){
        return new ResponseEntity<>(appRoleService.showAllRoles(), HttpStatus.OK);
    }



    @PostMapping(value = "/store", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppRoleResponseDTO> addOneAppRole(@RequestBody AppRoleRequestDTO appRole){
        appRoleService.addRole(appRole);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AppRole> deleteAppRole(@PathVariable("role") String role){
        appRoleService.deleteRole(role);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

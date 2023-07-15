package com.lundu.empback.controller;

import com.lundu.empback.dto.request.AppUserRequestDTO;
import com.lundu.empback.dto.response.AppUserResponseDTO;
import com.lundu.empback.entities.Employee;
import com.lundu.empback.servicies.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppUserResponseDTO>> getAllUser(){
        return new ResponseEntity<>(appUserService.showAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/show/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUserResponseDTO> getOneAppUser(@PathVariable("name") String name){
        return new ResponseEntity<>(appUserService.find(name), HttpStatus.OK);
    }

    @PostMapping(value = "/store", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUserResponseDTO> addOneAppUser(@RequestBody AppUserRequestDTO appUser){
        appUserService.save(appUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUserResponseDTO> updateAppUser(@PathVariable("id") String id, @RequestBody AppUserRequestDTO appUser){
        appUserService.update(id, appUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AppUserResponseDTO> deleteAppUser(@PathVariable("id") String id){
        appUserService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

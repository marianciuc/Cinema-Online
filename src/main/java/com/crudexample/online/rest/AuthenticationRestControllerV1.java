package com.crudexample.online.rest;

import com.crudexample.online.dto.AuthenticationRequestDto;
import com.crudexample.online.dto.RegistrationRequestDto;
import com.crudexample.online.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

    private AuthenticationService authenticationService;

    public AuthenticationRestControllerV1(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("register")
    public void register(@RequestBody RegistrationRequestDto registrationRequestDto) {
        authenticationService.register(registrationRequestDto);
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
       return ResponseEntity.status(HttpStatus.OK)
               .body(authenticationService.authenticate(authenticationRequestDto));
    }
}
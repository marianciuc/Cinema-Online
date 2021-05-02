package com.crudexample.online.service;

import com.crudexample.online.dto.AuthenticationRequestDto;
import com.crudexample.online.dto.RegistrationRequestDto;
import com.crudexample.online.dto.TokenDto;

public interface AuthenticationService {

    TokenDto authenticate(AuthenticationRequestDto authenticationRequestDto);

    void register(RegistrationRequestDto registrationRequestDto);

}

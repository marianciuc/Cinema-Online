package com.crudexample.online.dto;

import lombok.Data;

@Data
public class RegistrationRequestDto {
    private String username;
    private String password;
    private String email;
}

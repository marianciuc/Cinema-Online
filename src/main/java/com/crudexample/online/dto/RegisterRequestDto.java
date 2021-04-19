package com.crudexample.online.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterRequestDto {

    private String username;
    private String email;
}

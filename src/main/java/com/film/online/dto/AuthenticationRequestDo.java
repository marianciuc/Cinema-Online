package com.film.online.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDo {
    private String username;
    private String password;
}

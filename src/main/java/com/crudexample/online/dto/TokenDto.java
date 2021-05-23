package com.crudexample.online.dto;

import com.crudexample.online.model.User;
import lombok.Data;

@Data
public class TokenDto {
    private String token;
    private User user;
}

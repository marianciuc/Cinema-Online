package com.crudexample.online.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String oldPassword;
    private String newPassword;
}

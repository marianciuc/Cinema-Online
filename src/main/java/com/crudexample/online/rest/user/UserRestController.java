package com.crudexample.online.rest.user;

import com.crudexample.online.dto.ResetPasswordDto;
import com.crudexample.online.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/v1/user/")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "CHANGE_PASSWORD")
    public ResponseEntity changePassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        return userService.changePassword(resetPasswordDto.getOldPassword(), resetPasswordDto.getNewPassword());
    }
}

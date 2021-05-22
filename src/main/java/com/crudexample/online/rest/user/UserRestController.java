package com.crudexample.online.rest.user;

import com.crudexample.online.dto.GenreDto;
import com.crudexample.online.dto.ResetPasswordDto;
import com.crudexample.online.service.GenreService;
import com.crudexample.online.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/v1/user/")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("CHANGE_PASSWORD")
    public ResponseEntity changePassword(@Valid @RequestBody ResetPasswordDto resetPasswordDto) {
        return userService.changePassword(resetPasswordDto.getOldPassword(), resetPasswordDto.getNewPasswrod());
    }
}

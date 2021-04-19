package com.crudexample.online.rest;

import com.crudexample.online.dto.AuthenticationRequestDto;
import com.crudexample.online.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth/")
public class RegisterRestController {
//    @PostMapping(value = "register")
//    public ResponseEntity login(@RequestBody AuthenticationRequestDto authenticationRequestDto){
//        try {
//            String username = authenticationRequestDto.getUsername();
//
//            return ResponseEntity.ok(response);
//        }catch (AuthenticationException e){
//            System.out.println("error");
//            throw new BadCredentialsException("invalid username or password");
//        }

}

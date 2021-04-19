package com.crudexample.online.rest;

import com.crudexample.online.dto.AuthenticationRequestDto;
import com.crudexample.online.entity.User;
import com.crudexample.online.security.jwt.JwtTokenProvider;
import com.crudexample.online.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth/")
public class AuthenticationRestController {
    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;

    @GetMapping(value = "test")
    public ResponseEntity test(){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("Stepfather", "test"));
        User user = userService.findByUsername("Stepfather");
        System.out.println(user);
        String username = user.getUsername();
        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);

        return ResponseEntity.ok(response);
    }


    @PostMapping(value = "login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto authenticationRequestDto){
        try {
            String username = authenticationRequestDto.getUsername();
            String password = authenticationRequestDto.getPassword();
            System.out.println(password);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("auth ok");
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("user not found");
            }

            String token = jwtTokenProvider.createToken(username);

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        }catch (AuthenticationException e){
            throw new BadCredentialsException("invalid username or password");
        }
    }
}

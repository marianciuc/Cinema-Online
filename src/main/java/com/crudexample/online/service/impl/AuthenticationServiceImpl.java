package com.crudexample.online.service.impl;

import com.crudexample.online.dto.AuthenticationRequestDto;
import com.crudexample.online.dto.RegistrationRequestDto;
import com.crudexample.online.dto.TokenDto;
import com.crudexample.online.exceptions.UserAlreadyExistsException;
import com.crudexample.online.model.User;
import com.crudexample.online.repository.RoleRepository;
import com.crudexample.online.repository.UserRepository;
import com.crudexample.online.security.jwt.JwtTokenProvider;
import com.crudexample.online.service.AuthenticationService;
import com.crudexample.online.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    private String getToken(@org.jetbrains.annotations.NotNull User user, String password) {
        return jwtTokenProvider.createToken(user.getUsername());
    }

    @Override
    public TokenDto authenticate(AuthenticationRequestDto authenticationRequestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDto.getUsername(), authenticationRequestDto.getPassword()));
            User user = userService.findByUsername(authenticationRequestDto.getUsername());
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + authenticationRequestDto.getUsername() + " not found");
            }
            String token = getToken(user, authenticationRequestDto.getPassword());

            TokenDto tokenDto = new TokenDto();
            tokenDto.setToken(token);
            tokenDto.setUser(user);

            return tokenDto;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    public void register(RegistrationRequestDto registrationRequestDto) {
        if(userService.existsByUsername(registrationRequestDto.getUsername())){
            throw new UserAlreadyExistsException("User already exist");
        }
        userService.create(registrationRequestDto);
        return;
    }
}

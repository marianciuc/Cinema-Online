package com.crudexample.online.service;

import com.crudexample.online.dto.RegistrationRequestDto;
import com.crudexample.online.dto.UserDto;
import com.crudexample.online.model.User;

import java.util.List;

public interface UserService {

    User create(RegistrationRequestDto registrationRequestDto);

    List<User> getAll();

    User findByUsername(String username);

    boolean existsByUsername(String username);

    User findById(Long id);

    void delete(Long id);
}
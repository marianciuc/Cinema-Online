package com.crudexample.online.service;

import com.crudexample.online.dto.RegistrationRequestDto;
import com.crudexample.online.model.User;

import java.util.List;

public interface UserService {

    User register(RegistrationRequestDto user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);
}

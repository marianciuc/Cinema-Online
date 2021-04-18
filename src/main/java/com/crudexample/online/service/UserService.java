package com.crudexample.online.service;

import com.crudexample.online.entity.User;

import java.util.List;

public interface UserService {
    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void DeleteUser(Long id);
}

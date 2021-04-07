package com.film.online.service;

import com.film.online.entity.User;
import com.film.online.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User saveUser(User user) {
      return repository.save(user);
    }
    public List<User> saveUsers(List<User> users) {
        return repository.saveAll(users);
    }
}

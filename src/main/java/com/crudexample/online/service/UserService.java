package com.crudexample.online.service;

import com.crudexample.online.dto.RegistrationRequestDto;
import com.crudexample.online.model.Film;
import com.crudexample.online.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    User create(RegistrationRequestDto registrationRequestDto);

    List<User> getAll();

    User findByUsername(String username);

    boolean existsByUsername(String username);

    User findById(Long id);

    void deleteFilmFromList(String username, Long id);

    Set<Film> getUsersFilms(String username);

    void delete(Long id);
}
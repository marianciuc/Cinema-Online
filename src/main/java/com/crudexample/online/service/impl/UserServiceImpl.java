package com.crudexample.online.service.impl;

import com.crudexample.online.constant.MediaConstants;
import com.crudexample.online.dto.RegistrationRequestDto;
import com.crudexample.online.exceptions.RegistrationException;
import com.crudexample.online.model.Film;
import com.crudexample.online.model.Role;
import com.crudexample.online.model.Status;
import com.crudexample.online.model.User;
import com.crudexample.online.repository.RoleRepository;
import com.crudexample.online.repository.UserRepository;
import com.crudexample.online.security.jwt.JwtUser;
import com.crudexample.online.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(RegistrationRequestDto registrationRequestDto) {
        User user = new User();

        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setUsername(registrationRequestDto.getUsername());
        user.setEmail(registrationRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationRequestDto.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        user.setCreated(new Date());
        user.setUpdated(new Date());
        user.setBackgroundImageUrl(MediaConstants.PROFILE_DEFAULT_BACKGROUND);
        user.setMainPictureUrl("http://tinygraphs.com/squares/" + registrationRequestDto.getUsername() + "?fmt=svg");

        try {
            userRepository.save(user);
            return user;
        } catch (RegistrationException e) {
            throw new RegistrationException(e.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public ResponseEntity changePassword(String oldPassword, String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());

        if (user != null && passwordEncoder.matches(oldPassword, user.getPassword())){
            user.setPassword(passwordEncoder.encode(newPassword));
            return ResponseEntity.ok().body("Successful password change");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid password");
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public void deleteFilmFromList(String username, Long id) {
        User user = userRepository.findByUsername(username);

        Set<Film> filmList = user.getFilms();

        for (Film film : filmList) {
            if (film.getId().equals(id)) {
                filmList.remove(film);
            }
        }
        user.setFilms(filmList);
        userRepository.save(user);
    }

    @Override
    public Set<Film> getUsersFilms(String username) {
        User user = userRepository.findByUsername(username);

        Set<Film> filmList = user.getFilms();
        return filmList;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted");
    }
}
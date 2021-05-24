package com.crudexample.online.service.impl;

import com.crudexample.online.constant.MediaConstants;
import com.crudexample.online.dto.RegistrationRequestDto;
import com.crudexample.online.exceptions.RegistrationException;
import com.crudexample.online.model.Film;
import com.crudexample.online.model.Role;
import com.crudexample.online.model.Status;
import com.crudexample.online.model.User;
import com.crudexample.online.repository.FilmRepository;
import com.crudexample.online.repository.RoleRepository;
import com.crudexample.online.repository.UserRepository;
import com.crudexample.online.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final FilmRepository filmRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, FilmRepository filmRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.filmRepository = filmRepository;
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

        if (user != null && passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return ResponseEntity.ok().body("Successful password change");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid password");
    }

    @Override
    public ResponseEntity addFilmToList(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());

        Film film = filmRepository.findFilmById(id);
        if (film != null && user != null) {
            Set<Film> filmList = user.getFilms();

            filmList.add(film);
            user.setFilms(filmList);
            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.OK).body("Added");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not founded");
    }

    @Override
    public ResponseEntity deleteRoleFromUser(Long roleId, Long userID) {
        Optional<User> user = userRepository.findById(userID);

        if (user.isPresent()){
            List<Role> roleList = user.get().getRoles();
            for (Role role : roleList) {
                if (role.getId() == roleId){
                    roleList.remove(role);
                }
            }
            user.get().setRoles(roleList);
            return ResponseEntity.ok().body(userRepository.save(user.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error");
    }

    @Override
    public ResponseEntity addRoleUser(Long roleId, Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()){
            List<Role> roleList = user.get().getRoles();
            Optional<Role> role = roleRepository.findById(roleId);

            if (role.isPresent()){
                roleList.add(role.get());
                user.get().setRoles(roleList);
                System.out.println(roleId);
                return ResponseEntity.ok().body(userRepository.save(user.get()));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
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
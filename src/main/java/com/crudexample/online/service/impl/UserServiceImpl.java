package com.crudexample.online.service.impl;

import com.crudexample.online.constant.MediaConstants;
import com.crudexample.online.dto.RegistrationRequestDto;
import com.crudexample.online.dto.UserDto;
import com.crudexample.online.exceptions.RegistrationException;
import lombok.extern.slf4j.Slf4j;
import com.crudexample.online.model.Role;
import com.crudexample.online.model.Status;
import com.crudexample.online.model.User;
import com.crudexample.online.repository.RoleRepository;
import com.crudexample.online.repository.UserRepository;
import com.crudexample.online.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        user.setMainPictureUrl(MediaConstants.MAIN_PICTURE_URL);

        try{
            System.out.println(user);
            userRepository.save(user);
            return user;
        }catch (RegistrationException e){
            throw new RegistrationException(e.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
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
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted");
    }
}
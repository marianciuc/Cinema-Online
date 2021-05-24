package com.crudexample.online.service.impl;

import com.crudexample.online.dto.RoleDto;
import com.crudexample.online.model.Role;
import com.crudexample.online.model.Status;
import com.crudexample.online.repository.RoleRepository;
import com.crudexample.online.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void delete(Long id) {
        Optional<Role> role = roleRepository.findById(id);

        if (role.isPresent()){
            roleRepository.delete(role.get());
        }
    }

    @Override
    public ResponseEntity update(Long id, RoleDto roleDto) {
        Optional<Role> role = roleRepository.findById(id);

        if (role.isPresent()){
            role.get().setName(roleDto.getName());
            roleRepository.save(role.get());
            return ResponseEntity.ok().body("Successful changed");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not founded");
    }

    @Override
    public ResponseEntity create(RoleDto roleDto) {
        try {
            Role role = new Role();

            role.setName(roleDto.getName());
            role.setCreated(new Date());
            role.setStatus(Status.ACTIVE);
            role.setUpdated(new Date());

            return ResponseEntity.ok().body(roleRepository.save(role));
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Role already exist");
        }
    }

    @Override
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(roleRepository.findAll());
    }
}

package com.crudexample.online.service;

import com.crudexample.online.dto.RoleDto;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    void delete(Long id);
    ResponseEntity update(Long id, RoleDto roleDto);
    ResponseEntity create(RoleDto roleDto);
}

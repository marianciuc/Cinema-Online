package com.crudexample.online.rest.admin;

import com.crudexample.online.dto.RoleDto;
import com.crudexample.online.service.RoleService;
import com.crudexample.online.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/v1/admin/role/")
public class RoleAdminRestController {

    @Autowired
    private RoleService roleService;

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }

    @PostMapping("create")
    public ResponseEntity create(@Valid @RequestBody RoleDto roleDto) {
        return roleService.create(roleDto);
    }

    @PostMapping("update/{id}")
    public ResponseEntity update(@Valid @RequestBody RoleDto roleDto, @PathVariable Long id) {
        return roleService.update(id, roleDto);
    }
}

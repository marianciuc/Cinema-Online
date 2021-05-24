package com.crudexample.online.rest.admin;

import com.crudexample.online.constant.HttpStatuses;
import com.crudexample.online.dto.AdminUserDto;
import com.crudexample.online.model.User;
import com.crudexample.online.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/API/v1/ADMIN/USER/")
public class UserAdminRestController {

    @Autowired
    private UserService userService;

    @PostMapping("DELETE/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping(value = "GET/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "GET_ALL")
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.valueOf(HttpStatuses.OK))
                .body(userService.getAll());
    }

    @PostMapping("DELETE_ROLE/{id}/{user}")
    public ResponseEntity deleteRole(@PathVariable Long id, @PathVariable Long user){
        return userService.deleteRoleFromUser(id, user);
    }

    @PostMapping("ADD_ROLE/{id}/{user}")
    public ResponseEntity addRole(@PathVariable Long id, @PathVariable Long user){
        return userService.addRoleUser(id, user);
    }

}

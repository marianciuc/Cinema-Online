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
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;

    @Autowired
    public AdminRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "users/GET_ALL")
    @CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.OPTIONS})
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.valueOf(HttpStatuses.OK))
                .body(userService.getAll());
    }

}

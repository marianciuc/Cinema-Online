package com.crudexample.online.rest.user;

import com.crudexample.online.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/v1/genre/")
public class GenreRestController {

    @Autowired
    private GenreService genreService;

    @GetMapping("get")
    public ResponseEntity get() {
        return ResponseEntity.status(HttpStatus.OK).body(genreService.getAll());
    }
}

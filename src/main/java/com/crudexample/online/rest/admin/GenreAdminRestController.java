package com.crudexample.online.rest.admin;

import com.crudexample.online.dto.GenreDto;
import com.crudexample.online.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/v1/admin/genre/")
public class GenreAdminRestController {

    @Autowired
    private GenreService genreService;

    @PostMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return genreService.delete(id);
    }

    @PostMapping("add")
    public ResponseEntity add(@Valid @RequestBody GenreDto genreDto) {
        return genreService.create(genreDto);
    }

    @PostMapping("update/{id}")
    public ResponseEntity update(@Valid @RequestBody GenreDto genreDto, @PathVariable Long id) {
        return genreService.update(genreDto, id);
    }
}

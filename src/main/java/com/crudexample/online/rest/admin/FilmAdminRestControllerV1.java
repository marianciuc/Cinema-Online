package com.crudexample.online.rest.admin;

import com.crudexample.online.constant.HttpStatuses;
import com.crudexample.online.dto.FilmRequestDto;
import com.crudexample.online.repository.FilmRepository;
import com.crudexample.online.service.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/admin/film/")
public class FilmAdminRestControllerV1 {

    private FilmService filmService;

    public FilmAdminRestControllerV1(FilmService filmService, FilmRepository filmRepository) {
        this.filmService = filmService;
        this.filmRepository = filmRepository;
    }

    private FilmRepository filmRepository;

    @PostMapping("add")
    public ResponseEntity add(@RequestBody FilmRequestDto filmRequestDto) {
        return ResponseEntity.status(HttpStatus.valueOf(HttpStatuses.CREATED))
                .body(filmService.add(filmRequestDto));
    }


    @PostMapping("update/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody FilmRequestDto filmRequestDto) {
        System.out.println(filmRequestDto);
        return ResponseEntity.status(HttpStatus.valueOf(HttpStatuses.OK))
                .body(filmService.update(id, filmRequestDto));
    }

    @PostMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        filmService.delete(id);
    }


}

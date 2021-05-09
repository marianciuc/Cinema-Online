package com.crudexample.online.rest;

import com.crudexample.online.constant.HttpStatuses;
import com.crudexample.online.repository.FilmRepository;
import com.crudexample.online.service.FilmService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping(value = "/api/v1/open/film/")
public class FilmRestControllerV1 {

    private FilmService filmService;

    public FilmRestControllerV1(FilmService filmService, FilmRepository filmRepository) {
        this.filmService = filmService;
        this.filmRepository = filmRepository;
    }

    private FilmRepository filmRepository;

    @ApiOperation(value = "Search for a movie by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
    })
    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.valueOf(HttpStatuses.OK))
                .body(filmService.getById(id));
    }

    @ApiOperation(value = "Getting all films")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
    })
    @GetMapping("get")
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.valueOf(HttpStatuses.OK))
                .body(filmService.getAll());
    }
}

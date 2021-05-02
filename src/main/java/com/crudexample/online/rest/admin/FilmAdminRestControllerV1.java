package com.crudexample.online.rest.admin;

import com.crudexample.online.constant.HttpStatuses;
import com.crudexample.online.dto.FilmRequestDto;
import com.crudexample.online.repository.FilmRepository;
import com.crudexample.online.service.FilmService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiResponses;

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

    @ApiOperation(value = "Adding a new movie to the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
    })
    @PostMapping("add")
    public ResponseEntity add(@Valid @RequestBody FilmRequestDto filmRequestDto) {
        return ResponseEntity.status(HttpStatus.valueOf(HttpStatuses.CREATED))
                .body(filmService.add(filmRequestDto));
    }


    @ApiOperation(value = "Updates on film data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
    })
    @PostMapping("update/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody FilmRequestDto filmRequestDto) {
        return ResponseEntity.status(HttpStatus.valueOf(HttpStatuses.OK))
                .body(filmService.update(id, filmRequestDto));
    }

    @ApiOperation(value = "Changing the status of a movie to DELETED")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
    })
    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        filmService.delete(id);
    }


}

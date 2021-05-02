package com.crudexample.online.rest.admin;

import com.crudexample.online.constant.HttpStatuses;
import com.crudexample.online.dto.FilmRequestDto;
import com.crudexample.online.repository.FilmRepository;
import com.crudexample.online.service.FilmService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiResponses;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
            @ApiResponse(code = 500, message = HttpStatuses.TOKEN_EXPIRED)
    })
    @PostMapping("add")
    public ResponseEntity login(@Valid @RequestBody FilmRequestDto filmRequestDto) {
        return ResponseEntity.status(HttpStatus.valueOf(HttpStatuses.CREATED))
                .body(filmService.addFilm(filmRequestDto));
    }
}

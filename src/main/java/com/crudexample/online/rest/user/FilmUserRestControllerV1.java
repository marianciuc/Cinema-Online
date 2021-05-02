package com.crudexample.online.rest.user;

import com.crudexample.online.constant.HttpStatuses;
import com.crudexample.online.dto.FilmRequestDto;
import com.crudexample.online.repository.FilmRepository;
import com.crudexample.online.service.FilmService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/film/")
public class FilmUserRestControllerV1 {

    private FilmService filmService;

    public FilmUserRestControllerV1(FilmService filmService, FilmRepository filmRepository) {
        this.filmService = filmService;
        this.filmRepository = filmRepository;
    }

    private FilmRepository filmRepository;

    @ApiOperation(value = "User rating of a movie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = HttpStatuses.OK),
            @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
            @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
    })
    @PostMapping("{id}/{mark}")
    public void rate(@PathVariable Long id, @PathVariable Integer mark) {
        filmService.rate(id, mark);
    }
}

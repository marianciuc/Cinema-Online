package com.crudexample.online.rest.user;

import com.crudexample.online.constant.HttpStatuses;
import com.crudexample.online.dto.MessageResponseDto;
import com.crudexample.online.repository.FilmRepository;
import com.crudexample.online.security.jwt.JwtTokenProvider;
import com.crudexample.online.service.FilmService;
import com.crudexample.online.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/v1/film/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FilmUserRestControllerV1 {

    private FilmService filmService;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;

    public FilmUserRestControllerV1(FilmService filmService, JwtTokenProvider jwtTokenProvider, UserService userService, FilmRepository filmRepository) {
        this.filmService = filmService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
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


    @PostMapping("/ADD_TO_VIEWED_LIST/{id}")
    public void addToList(@PathVariable Long id){
        userService.addFilmToList(id);
    }


    @GetMapping("GET_USER_FILMS")
    public ResponseEntity getUserFilms(HttpServletRequest req) {

        String username = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req));

        return ResponseEntity.status(HttpStatus.valueOf(HttpStatuses.OK))
                .body(userService.getUsersFilms(username));
    }

    @PostMapping("DELETE_FROM_LIST/{id}")
    public ResponseEntity deleteFromList(HttpServletRequest req, @PathVariable Long id) {
        String username = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req));
        userService.deleteFilmFromList(username, id);

        MessageResponseDto messageResponseDto = new MessageResponseDto();
        messageResponseDto.setMessage("ok");

        return ResponseEntity.ok().body(messageResponseDto);
    }
}

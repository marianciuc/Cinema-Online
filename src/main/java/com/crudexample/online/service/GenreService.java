package com.crudexample.online.service;

import com.crudexample.online.dto.GenreDto;
import com.crudexample.online.model.Genre;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    ResponseEntity delete(Long id);
    ResponseEntity create(GenreDto genreDto);

    ResponseEntity update(GenreDto genreDto, Long id);
}

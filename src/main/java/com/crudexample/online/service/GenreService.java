package com.crudexample.online.service;

import com.crudexample.online.dto.GenreDto;
import com.crudexample.online.model.Genre;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    ResponseEntity create(GenreDto genreDto);

    ResponseEntity update(GenreDto genreDto, Long id);
}

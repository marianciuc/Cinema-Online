package com.crudexample.online.service;

import com.crudexample.online.dto.FilmRequestDto;
import com.crudexample.online.model.Film;

public interface FilmService {

    Film addFilm(FilmRequestDto filmRequestDto);

    void deleteFilmById(Long id);

    void updateFilm(FilmRequestDto filmRequestDto);
}

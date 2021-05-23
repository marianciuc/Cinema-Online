package com.crudexample.online.service;

import com.crudexample.online.dto.FilmRequestDto;
import com.crudexample.online.model.Film;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface FilmService {

    Set<Film> getLastReleased();

    Set<Film> findByName(String name);

    Set<Film> getMostPopular();

    Film add(FilmRequestDto filmRequestDto);

    void delete(Long id);

    Film getById(Long id);

    ResponseEntity getStatus(Long id);

    List<Film> getAll();

    void save(Film film);

    void rate(Long id, Integer mark);

    Film update(Long id, FilmRequestDto filmRequestDto);
}

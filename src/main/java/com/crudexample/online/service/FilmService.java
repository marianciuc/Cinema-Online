package com.crudexample.online.service;

import com.crudexample.online.dto.FilmRequestDto;
import com.crudexample.online.model.Film;
import javassist.NotFoundException;

import javax.management.BadAttributeValueExpException;
import java.util.List;

public interface FilmService {

    Film add(FilmRequestDto filmRequestDto);

    void delete(Long id);

    Film getById(Long id);

    List<Film> getAll();

    void rate(Long id, Integer mark);

    Film update(Long id, FilmRequestDto filmRequestDto);
}

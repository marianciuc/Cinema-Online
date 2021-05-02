package com.crudexample.online.service.impl;

import com.crudexample.online.dto.FilmRequestDto;
import com.crudexample.online.model.Film;
import com.crudexample.online.model.FilmStatus;
import com.crudexample.online.model.Status;
import com.crudexample.online.repository.FilmRepository;
import com.crudexample.online.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Service
@Slf4j
public class FilmServiceImpl implements FilmService {

    private FilmRepository filmRepository;
    private ModelMapper modelMapper;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository, ModelMapper modelMapper) {
        this.filmRepository = filmRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Film addFilm( FilmRequestDto filmRequestDto) {
        if(filmRequestDto == null){
            throw new NullPointerException("Film data is empty");
        }

        Film saveFilm = modelMapper.map(filmRequestDto, Film.class);
        saveFilm.setStatus(Status.ACTIVE);
        saveFilm.setCreated(new Date());
        saveFilm.setUpdated(new Date());
        saveFilm.setScore(0);
        saveFilm.setVotes(0);

        filmRepository.save(saveFilm);
        log.info("Film {} successful saved", saveFilm.getName());

        return saveFilm;
    }

    @Override
    public void deleteFilmById(Long id) {
        Film film = filmRepository.findFilmById(id);

        if (film == null){
            log.warn("IN findById - no film found by id: {}", id);
        }

        film.setStatus(Status.DELETED);

        filmRepository.save(film);
    }

    @Override
    public void updateFilm(FilmRequestDto filmRequestDto) {

    }
}

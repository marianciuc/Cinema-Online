package com.crudexample.online.service.impl;

import com.crudexample.online.dto.FilmRequestDto;
import com.crudexample.online.exceptions.IncorrectIdException;
import com.crudexample.online.model.Film;
import com.crudexample.online.model.Status;
import com.crudexample.online.repository.FilmRepository;
import com.crudexample.online.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public Film add(FilmRequestDto filmRequestDto) {
        if (filmRequestDto == null) {
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
    public void delete(Long id) {
        Film film = filmRepository.findFilmById(id);

        if (film == null) {
            log.warn("IN findById - no film found by id: {}", id);
        }

        film.setStatus(Status.DELETED);

        filmRepository.save(film);
    }

    @Override
    public Film getById(Long id) {
        Optional<Film> optionalFilm = filmRepository.findById(id);
        if (optionalFilm.isPresent()) {
            return optionalFilm.get();
        }
        throw new IncorrectIdException("Id not founded");
    }

    @Override
    public List<Film> getAll() {
        List<Film> filmList = filmRepository.findAll();
        log.info("founded {} films", filmList.size());
        return filmList;
    }

    @Override
    public void rate(Long id, Integer mark) {
        Optional<Film> optionalFilm = filmRepository.findById(id);

        if (optionalFilm.isPresent()) {

            optionalFilm.get().setVotes(optionalFilm.get().getVotes() + 1);
            optionalFilm.get().setScore(optionalFilm.get().getScore() + mark);

            filmRepository.save(optionalFilm.get());
            return;
        }
        throw new IncorrectIdException("Id not founded");
    }

    @Override
    public Film update(Long id, FilmRequestDto filmRequestDto) {
        Film toUpdate = filmRepository.findFilmById(id);

        toUpdate.setUpdated(new Date());
        toUpdate.setBackground(filmRequestDto.getBackground());
        toUpdate.setPoster(filmRequestDto.getPoster());
        toUpdate.setName(filmRequestDto.getName());
        toUpdate.setDescription(filmRequestDto.getDescription());
        toUpdate.setEpisodes(filmRequestDto.getEpisodes());
        toUpdate.setKpkId(filmRequestDto.getKpkId());
        toUpdate.setTrailer(filmRequestDto.getTrailer());
        toUpdate.setGenres(filmRequestDto.getGenres());

        filmRepository.save(toUpdate);

        log.info("Film {} information has been successfully updated", filmRequestDto.getName());
        return modelMapper.map(toUpdate, Film.class);
    }
}

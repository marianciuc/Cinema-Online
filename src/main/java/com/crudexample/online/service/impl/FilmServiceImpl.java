package com.crudexample.online.service.impl;

import com.crudexample.online.dto.FilmRequestDto;
import com.crudexample.online.exceptions.IncorrectIdException;
import com.crudexample.online.model.Film;
import com.crudexample.online.model.Genre;
import com.crudexample.online.model.Status;
import com.crudexample.online.repository.FilmRepository;
import com.crudexample.online.repository.GenreRepository;
import com.crudexample.online.service.FilmService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilmServiceImpl implements FilmService {

    private FilmRepository filmRepository;
    private ModelMapper modelMapper;
    private GenreRepository genreRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository, ModelMapper modelMapper, GenreRepository genreRepository) {
        this.filmRepository = filmRepository;
        this.modelMapper = modelMapper;
        this.genreRepository = genreRepository;
    }


    @Override
    public Set<Film> getLastReleased() {
        return filmRepository.findFirst8ByOrderByCreatedDesc();
    }

    @Override
    public Set<Film> findByName(String name) {
        return filmRepository.findByNameContainingOrderByName(name);
    }

    @Override
    public Set<Film> getMostPopular() {
        return filmRepository.findFirst8ByOrderByVotesDesc();
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

        List<Genre> genreList = new ArrayList<>();
        List<Long> idList = filmRequestDto.getGenresIds();


        for (Long value: idList) {
            Optional<Genre> temp = genreRepository.findById(value);
            if (temp.isPresent()){
                genreList.add(temp.get());
            }
        }

        saveFilm.setGenres(genreList);

        filmRepository.save(saveFilm);
        return saveFilm;
    }

    @Override
    public void delete(Long id) {
        Film film = filmRepository.findFilmById(id);
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

        List<Genre> genreList = new ArrayList<>();
        List<Long> idList = filmRequestDto.getGenresIds();


        for (Long value: idList) {
           Optional<Genre> temp = genreRepository.findById(value);
           if (temp.isPresent()){
               genreList.add(temp.get());
           }
        }

        toUpdate.setGenres(genreList);

        filmRepository.save(toUpdate);
        return modelMapper.map(toUpdate, Film.class);
    }
}

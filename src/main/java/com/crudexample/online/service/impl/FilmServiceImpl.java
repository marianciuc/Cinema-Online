package com.crudexample.online.service.impl;

import com.crudexample.online.dto.FilmRequestDto;
import com.crudexample.online.exceptions.IncorrectIdException;
import com.crudexample.online.model.Film;
import com.crudexample.online.model.Genre;
import com.crudexample.online.model.Status;
import com.crudexample.online.model.User;
import com.crudexample.online.repository.FilmRepository;
import com.crudexample.online.repository.GenreRepository;
import com.crudexample.online.service.FilmService;
import com.crudexample.online.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilmServiceImpl implements FilmService {

    private FilmRepository filmRepository;
    private ModelMapper modelMapper;
    private GenreRepository genreRepository;
    private UserService userService;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository, ModelMapper modelMapper, GenreRepository genreRepository, UserService userService) {
        this.filmRepository = filmRepository;
        this.modelMapper = modelMapper;
        this.genreRepository = genreRepository;
        this.userService = userService;
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


        for (Long value : idList) {
            Optional<Genre> temp = genreRepository.findById(value);
            if (temp.isPresent()) {
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
        filmRepository.delete(film);
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
    public ResponseEntity getStatus(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName());

        Film film = filmRepository.findFilmById(id);

        if (user != null && film != null) {
            for (User _u : film.getUsers()){
                if (_u.getId() == user.getId()){
                    return ResponseEntity.status(HttpStatus.OK).body("Founded");
                }
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not founded");
    }

    @Override
    public List<Film> getAll() {
        List<Film> filmList = filmRepository.findAll();
        return filmList;
    }

    @Override
    public void save(Film film) {
        filmRepository.save(film);
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
        toUpdate.setEpisodes(filmRequestDto.getEpisodes());
        toUpdate.setTrailer(filmRequestDto.getTrailer());

        filmRepository.save(toUpdate);
        return modelMapper.map(toUpdate, Film.class);
    }
}

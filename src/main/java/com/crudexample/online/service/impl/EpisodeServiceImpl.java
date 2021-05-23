package com.crudexample.online.service.impl;

import com.crudexample.online.dto.EpisodeDto;
import com.crudexample.online.model.Episode;
import com.crudexample.online.model.Film;
import com.crudexample.online.model.Status;
import com.crudexample.online.repository.EpisodeRepository;
import com.crudexample.online.service.EpisodeService;
import com.crudexample.online.service.FilmService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EpisodeServiceImpl implements EpisodeService {

    private ModelMapper modelMapper;
    private FilmService filmService;
    private EpisodeRepository episodeRepository;

    public EpisodeServiceImpl(ModelMapper modelMapper, FilmService filmService, EpisodeRepository episodeRepository) {
        this.modelMapper = modelMapper;
        this.filmService = filmService;
        this.episodeRepository = episodeRepository;
    }


    @Override
    public ResponseEntity add(EpisodeDto episodeDto, Long filmID) {
        Episode newEpisode = modelMapper.map(episodeDto, Episode.class);
        Film film = filmService.getById(filmID);

        if (film != null){
            newEpisode.setStatus(Status.ACTIVE);
            newEpisode.setCreated(new Date());
            newEpisode.setUpdated(new Date());
            newEpisode.setFilm(film);

            return ResponseEntity.status(HttpStatus.OK).body(episodeRepository.save(newEpisode));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not founded");
    }

    @Override
    public ResponseEntity update(EpisodeDto episodeDto, Long id) {
        Optional<Episode> toUpdate = episodeRepository.findById(id);

        if (toUpdate.isPresent()){
            toUpdate.get().setNumber((Integer) episodeDto.getNumber());
            toUpdate.get().setSrc(episodeDto.getSrc());

            return  ResponseEntity.ok().body(episodeRepository.save(toUpdate.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Founded");
    }

    @Override
    public ResponseEntity delete(Long id) {
        Optional<Episode> toUpdate = episodeRepository.findById(id);

        if (toUpdate.isPresent()){
           episodeRepository.delete(toUpdate.get());
            return  ResponseEntity.ok().body("Successful deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Founded");
    }
}

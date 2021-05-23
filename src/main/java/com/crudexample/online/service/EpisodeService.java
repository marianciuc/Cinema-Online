package com.crudexample.online.service;

import com.crudexample.online.dto.EpisodeDto;
import com.crudexample.online.dto.FilmRequestDto;
import org.springframework.http.ResponseEntity;

public interface EpisodeService {
    ResponseEntity add(EpisodeDto episodeDto, Long id);
    ResponseEntity update(EpisodeDto episodeDto, Long id);
    ResponseEntity delete(Long id);
}

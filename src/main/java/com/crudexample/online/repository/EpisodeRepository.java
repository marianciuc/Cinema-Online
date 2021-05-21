package com.crudexample.online.repository;

import com.crudexample.online.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    List<Episode> findAllByFilmName(String name);
}

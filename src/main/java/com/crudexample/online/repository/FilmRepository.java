package com.crudexample.online.repository;

import com.crudexample.online.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FilmRepository extends JpaRepository<Film, Long> {

    Film findFilmById(Long id);

    Set<Film> findFirst8ByOrderByVotesDesc();

    Set<Film> findByNameContainingOrderByName(String name);

    Set<Film> findFirst8ByOrderByCreatedDesc();
}

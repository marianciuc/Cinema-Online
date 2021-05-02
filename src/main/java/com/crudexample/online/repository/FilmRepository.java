package com.crudexample.online.repository;

import com.crudexample.online.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
    Film findFilmById(Long id);
}

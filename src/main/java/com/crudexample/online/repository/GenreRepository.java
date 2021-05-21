package com.crudexample.online.repository;

import com.crudexample.online.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findOneByName(String name);
 }

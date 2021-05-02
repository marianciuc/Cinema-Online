package com.crudexample.online.dto;

import com.crudexample.online.model.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "relationClass"})
public class FilmRequestDto {
    private String name;
    private Integer kpkId;
    private String trailer;
    private String poster;
    private String background;
    private String description;
    private Integer episodes;
    private List<Genre> genres;
}

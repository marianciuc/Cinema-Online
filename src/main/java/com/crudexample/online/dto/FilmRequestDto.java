package com.crudexample.online.dto;

import lombok.Data;

@Data
public class FilmRequestDto {
    private String name;
    private Integer kpkId;
    private String trailer;
    private String poster;
    private String background;
    private String description;
    private Integer episodes;
}

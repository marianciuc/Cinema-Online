package com.crudexample.online.dto;

import com.crudexample.online.model.Film;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EpisodeDto {
    private String src;
    private Number number;
}

package com.crudexample.online.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "episodes")
public class Episode extends BaseEntity{

    @Column(name = "number")
    private Integer number;

    @Column(name = "src")
    private String src;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "film_id")
    private Film film;
}

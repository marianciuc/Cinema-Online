package com.crudexample.online.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "episodes")
public class Episode extends BaseEntity{

    @Column(name = "number")
    private Integer number;

    @Column(name = "src")
    private String src;

    @JsonIgnore
    @ManyToMany(mappedBy = "episodes", fetch = FetchType.LAZY)
    private List<Film> films;
}

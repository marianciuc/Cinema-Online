package com.crudexample.online.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "films")
@Data
public class Film extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "kpk_id")
    private Integer kpkId;

    @Column(name = "score")
    private Integer score;

    @Column(name = "votes")
    private Integer votes;

    @Column(name = "trailer")
    private String trailer;

    @Column(name = "poster")
    private String poster;

    @Column(name = "episodes")
    private Integer episodes;

    @Column(name = "description")
    private String description;

    @Column(name = "background")
    private String background;

    @ManyToMany(mappedBy = "films", fetch = FetchType.LAZY)
    private List<User> users;
}

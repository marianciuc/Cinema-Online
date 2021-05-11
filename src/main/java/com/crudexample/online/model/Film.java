package com.crudexample.online.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @JsonIgnore
    @ManyToMany(mappedBy = "films", fetch = FetchType.LAZY)
    private Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "genres_films",
            joinColumns = {@JoinColumn(name = "film_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "id")})
    private List<Genre> genres;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "characters_films",
            joinColumns = {@JoinColumn(name = "film_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "character_id", referencedColumnName = "id")})
    private Set<Character> characters;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "film", orphanRemoval = true)
    private List<Episode> episodesData;
}

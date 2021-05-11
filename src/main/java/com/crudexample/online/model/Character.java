package com.crudexample.online.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "characters")
public class Character extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "picture")
    private String picture;

    @JsonIgnore
    @ManyToMany(mappedBy = "characters", fetch = FetchType.LAZY)
    private Set<Film> films;
}

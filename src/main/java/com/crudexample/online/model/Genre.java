package com.crudexample.online.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "genre")
@Data
public class Genre extends BaseEntity {

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private Set<Film> films;
}

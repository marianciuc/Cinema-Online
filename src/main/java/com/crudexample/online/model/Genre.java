package com.crudexample.online.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "genre")
@Data
public class Genre extends BaseEntity {

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private List<Film> films;
}

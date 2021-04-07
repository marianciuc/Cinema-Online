package com.film.online.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "character")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Character extends BaseEntity {

    @Column(name = "picture")
    private String picture;

    @Column(name = "name")
    private String name;
}

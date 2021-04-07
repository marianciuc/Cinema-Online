package com.film.online.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "film")
public class Film extends BaseEntity{


}

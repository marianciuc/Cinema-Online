package com.crudexample.online.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "comments")
public class Comments extends BaseEntity{
    @Column(name = "text")
    private String text;

    @JsonIgnore
    @ManyToMany(mappedBy = "comments", fetch = FetchType.LAZY)
    private List<User> users;
}

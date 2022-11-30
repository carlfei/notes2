package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Libros {

    @Id
    private Integer id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String titulo;
    @Column
    private String autor;


}

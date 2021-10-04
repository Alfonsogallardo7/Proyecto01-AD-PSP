package com.salesianostriana.Proyecto01.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Artista {
    @Id
    @GeneratedValue
    private Long Id;

    private String Nombre;


}

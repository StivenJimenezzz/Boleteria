package com.boleteria.Boleteria.Modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String fecha;
    private String hora;
    private String lugar;
    private String patrocinador;

    private int boletasA;
    private int boletasB;
    private int boletasC;
}
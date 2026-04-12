package com.boleteria.Boleteria.Modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor


public class Comprador {
    private String nombre;
    private String documento;
    private String correo;
    private String telefono;
}

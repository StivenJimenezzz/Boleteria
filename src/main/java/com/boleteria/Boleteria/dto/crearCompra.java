package com.boleteria.Boleteria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.modelo.MetodoPago;

@Getter
@Setter
@AllArgsConstructor


public class crearCompra {
    private String nombreEvento;
    private String nombreComprador;
    private MetodoPago metodoPago;
}

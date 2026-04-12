package com.boleteria.Boleteria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.boleteria.Boleteria.Modelo.MetodoPago;

@Getter
@Setter
@AllArgsConstructor


public class crearCompra {
    private String nombreEvento;
    private String nombreComprador;
    private MetodoPago metodoPago;
}

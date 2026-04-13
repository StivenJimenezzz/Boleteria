package com.boleteria.Boleteria.dto;

import lombok.*;
import com.boleteria.Boleteria.Modelo.MetodoPago;
import com.boleteria.Boleteria.Modelo.Zona;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CrearCompra {

    private Long eventoId;

    private String nombreComprador;
    private String identificacion;

    private Zona zona;
    private int cantidad;

    private MetodoPago metodoPago;
}
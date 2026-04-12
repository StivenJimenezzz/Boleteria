package com.boleteria.Boleteria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.boleteria.Boleteria.Modelo.EstadoCompra;
import com.boleteria.Boleteria.Modelo.MetodoPago;
import com.boleteria.Boleteria.Modelo.Zona;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor


public class Compra {
    private String nombreEvento;
    private String nombreComprador;
    private double precio;
    private MetodoPago metodoPago;
    private EstadoCompra estadoCompra;
    private LocalDateTime fechaReserva;
    private String numeroComprobante;
    private Map<Zona, Integer> boletasPorZona;
}

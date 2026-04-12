package com.boleteria.Boleteria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.modelo.EstadoCompra;
import org.example.modelo.MetodoPago;
import org.example.modelo.Zona;

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

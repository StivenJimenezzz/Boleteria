package com.boleteria.Boleteria.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor


public class CompraBoletas {
    private Evento evento;
    private Comprador comprador;
    private Map<Zona, Integer> boletasPorZona;
    private double valorTotal;
    private MetodoPago metodoPago;
    private EstadoCompra estadoCompra;
    private LocalDateTime fechaReserva;
    private String numeroComprobante;
}

package com.boleteria.Boleteria.Modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraBoletas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Evento evento;

    private String nombreComprador;
    private String identificacion;

    @Enumerated(EnumType.STRING)
    private Zona zona;

    private int cantidad;
    private double total;


    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    @Enumerated(EnumType.STRING)
    private EstadoCompra estado;

    private String comprobante;
}
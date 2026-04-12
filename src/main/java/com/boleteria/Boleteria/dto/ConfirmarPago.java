package com.boleteria.Boleteria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor


public class ConfirmarPago {
    private String idCompra;
    private String numeroComprobante;

}

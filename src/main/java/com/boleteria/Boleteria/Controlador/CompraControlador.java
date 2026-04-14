package com.boleteria.Boleteria.Controlador;

import com.boleteria.Boleteria.Modelo.CompraBoletas;
import com.boleteria.Boleteria.Modelo.Evento;
import com.boleteria.Boleteria.Servicio.CompraServicio;
import com.boleteria.Boleteria.Servicio.EventoServicio;
import com.boleteria.Boleteria.dto.ConfirmarPago;
import com.boleteria.Boleteria.dto.CrearCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
@CrossOrigin(origins = "*")
public class CompraControlador {

    @Autowired
    private CompraServicio compraServicio;

    @Autowired
    private EventoServicio eventoServicio;

    // RQ 02 - crear compra
    @PostMapping
    public CompraBoletas comprar(@RequestBody CrearCompra request) {
        return compraServicio.comprar(request);
    }

    // RQ 03 - confirmar pago
    @PostMapping("/pago")
    public CompraBoletas pagar(@RequestBody ConfirmarPago request) {
        return compraServicio.confirmarPago(request);
    }

    // RQ 04 - historial
    @GetMapping("/historial/{identificacion}")
    public List<CompraBoletas> historial(@PathVariable String identificacion) {
        return compraServicio.historial(identificacion);
    }
}
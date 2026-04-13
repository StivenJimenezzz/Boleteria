package com.boleteria.Boleteria.Servicio;

import com.boleteria.Boleteria.Modelo.*;
import com.boleteria.Boleteria.Repositorio.CompraRepositorio;
import com.boleteria.Boleteria.Repositorio.EventoRepositorio;
import com.boleteria.Boleteria.dto.CrearCompra;
import com.boleteria.Boleteria.dto.ConfirmarPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraServicio {

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private CompraRepositorio compraRepositorio;

    // RQ 02
    public CompraBoletas comprar(CrearCompra request) {

        Evento evento = eventoRepositorio.findById(request.getEventoId())
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        int cantidad = request.getCantidad();

        if (cantidad <= 0 || cantidad > 10) {
            throw new RuntimeException("Cantidad inválida");
        }

        double precio = 0;

        switch (request.getZona()) {

            case A:
                if (evento.getBoletasA() < cantidad)
                    throw new RuntimeException("No hay boletas A");
                evento.setBoletasA(evento.getBoletasA() - cantidad);
                precio = 200;
                break;

            case B:
                if (evento.getBoletasB() < cantidad)
                    throw new RuntimeException("No hay boletas B");
                evento.setBoletasB(evento.getBoletasB() - cantidad);
                precio = 100;
                break;

            case C:
                if (evento.getBoletasC() < cantidad)
                    throw new RuntimeException("No hay boletas C");
                evento.setBoletasC(evento.getBoletasC() - cantidad);
                precio = 50;
                break;

            default:
                throw new RuntimeException("Zona inválida");
        }

        double total = precio * cantidad;

        CompraBoletas compra = new CompraBoletas();
        compra.setEvento(evento);
        compra.setNombreComprador(request.getNombreComprador());
        compra.setIdentificacion(request.getIdentificacion());
        compra.setZona(request.getZona());
        compra.setCantidad(cantidad);
        compra.setTotal(total);
        compra.setMetodoPago(request.getMetodoPago());
        compra.setEstado(EstadoCompra.RESERVADA);

        eventoRepositorio.save(evento);
        return compraRepositorio.save(compra);
    }

    // RQ 03
    public CompraBoletas confirmarPago(ConfirmarPago request) {

        if (request.getNumeroComprobante() == null || request.getNumeroComprobante().isEmpty()) {
            throw new RuntimeException("Comprobante requerido");
        }

        Long id = Long.parseLong(request.getIdCompra());

        CompraBoletas compra = compraRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        if (compra.getEstado() != EstadoCompra.RESERVADA) {
            throw new RuntimeException("La compra no está pendiente de pago");
        }

        compra.setEstado(EstadoCompra.PAGADA);
        compra.setComprobante(request.getNumeroComprobante());

        return compraRepositorio.save(compra);
    }
    // RQ 04
    public List<CompraBoletas> historial(String identificacion) {

        List<CompraBoletas> compras = compraRepositorio
                .findByIdentificacion(identificacion);

        if (compras.isEmpty()) {
            throw new RuntimeException("No hay compras para este usuario");
        }

        return compras;
    }
}
package com.boleteria.Boleteria.Controlador;

import com.boleteria.Boleteria.Modelo.Evento;
import com.boleteria.Boleteria.Servicio.EventoServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoControlador {

    private final EventoServicio eventoServicio;

    public EventoControlador(EventoServicio eventoServicio) {
        this.eventoServicio = eventoServicio;
    }

    // 🔥 Crear evento
    @PostMapping
    public Evento crearEvento(@RequestBody Evento evento) {
        return eventoServicio.crearEvento(evento);
    }

    // 🔥 Listar eventos
    @GetMapping
    public List<Evento> listarEventos() {
        return eventoServicio.listarEventos();
    }
}
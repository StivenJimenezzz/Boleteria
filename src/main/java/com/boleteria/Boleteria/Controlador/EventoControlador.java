package com.boleteria.Boleteria.Controlador;

import com.boleteria.Boleteria.Modelo.Evento;
import com.boleteria.Boleteria.Servicio.EventoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
@CrossOrigin(origins = "*")
public class EventoControlador {

    @Autowired
    private EventoServicio eventoServicio;

    // RQ 01 - CREAR EVENTO
    @PostMapping
    public Evento crearEvento(@RequestBody Evento evento) {
        return eventoServicio.crearEvento(evento);
    }

    // RQ 05 LISTAR EVENTOS
    @GetMapping("/disponibles")
    public List<Evento> listarDisponibles() {
        return eventoServicio.listarEventos();
    }
}
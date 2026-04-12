package com.boleteria.Boleteria.Servicio;

import com.boleteria.Boleteria.Modelo.Evento;
import com.boleteria.Boleteria.Repositorio.EventoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoServicio {

    private final EventoRepositorio eventoRepository;

    // Constructor
    public EventoServicio(EventoRepositorio eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    // se crea evento
    public Evento crearEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    //  se lista evento
    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }
}
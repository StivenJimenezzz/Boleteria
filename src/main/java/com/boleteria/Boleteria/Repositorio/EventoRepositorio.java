package com.boleteria.Boleteria.Repositorio;

import com.boleteria.Boleteria.Modelo.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepositorio extends JpaRepository<Evento, Long> {
}

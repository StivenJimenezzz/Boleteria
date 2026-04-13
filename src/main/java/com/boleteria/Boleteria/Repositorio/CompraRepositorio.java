package com.boleteria.Boleteria.Repositorio;

import com.boleteria.Boleteria.Modelo.CompraBoletas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompraRepositorio extends JpaRepository<CompraBoletas, Long> {

    List<CompraBoletas> findByIdentificacion(String identificacion);
}
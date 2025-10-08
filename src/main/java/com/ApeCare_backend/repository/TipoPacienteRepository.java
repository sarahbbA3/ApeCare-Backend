package com.ApeCare_backend.repository;

import com.ApeCare_backend.entity.TipoPaciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoPacienteRepository extends JpaRepository<TipoPaciente, Long> {
    List<TipoPaciente> findByEstadoNombreIgnoreCase(String estado);
}

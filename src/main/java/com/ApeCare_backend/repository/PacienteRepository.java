package com.ApeCare_backend.repository;

import com.ApeCare_backend.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByEstadoNombreIgnoreCase(String estado);
    Optional<Paciente> findByCedula(String cedula);

}

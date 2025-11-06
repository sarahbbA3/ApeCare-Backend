package com.ApeCare_backend.repository;

import com.ApeCare_backend.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findByEstadoNombreIgnoreCase(String estado);
    Optional<Medico> findByCedula(String cedula);
    Optional<Medico> findByUsuarioId(Long usuarioId);
}

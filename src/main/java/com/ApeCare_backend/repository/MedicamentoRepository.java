package com.ApeCare_backend.repository;

import com.ApeCare_backend.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    List<Medicamento> findByEstadoNombreIgnoreCase(String estado);
    long countByUbicacionIdAndEstadoNombreIgnoreCase(Long ubicacionId, String estado);
}

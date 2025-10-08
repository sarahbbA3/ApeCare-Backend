package com.ApeCare_backend.repository;

import com.ApeCare_backend.entity.MedicamentoSuministrado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentoSuministradoRepository extends JpaRepository<MedicamentoSuministrado, Long> {
    List<MedicamentoSuministrado> findByEstadoNombreIgnoreCase(String estado);
}

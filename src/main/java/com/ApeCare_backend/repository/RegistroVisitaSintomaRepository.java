package com.ApeCare_backend.repository;

import com.ApeCare_backend.entity.RegistroVisitaSintoma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroVisitaSintomaRepository extends JpaRepository<RegistroVisitaSintoma, Long> {
    List<RegistroVisitaSintoma> findByEstadoNombreIgnoreCase(String estado);
}

package com.ApeCare_backend.repository;

import com.ApeCare_backend.entity.TandaLabor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TandaLaborRepository extends JpaRepository<TandaLabor, Long> {
    List<TandaLabor> findByEstadoNombreIgnoreCase(String estado);

}

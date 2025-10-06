package com.ApeCare_backend.repository;

import com.ApeCare_backend.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Estado findByNombre(String nombre);
}

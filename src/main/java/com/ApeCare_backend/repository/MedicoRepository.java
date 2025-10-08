package com.ApeCare_backend.repository;

import com.ApeCare_backend.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
	List<Medico> findByEstadoNombreIgnoreCase(String estado);
}

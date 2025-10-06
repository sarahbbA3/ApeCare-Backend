package com.ApeCare_backend.repository;

import com.ApeCare_backend.entity.TipoFarmaco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoFarmacoRepository extends JpaRepository<TipoFarmaco, Long> {

    List<TipoFarmaco> findByEstadoNombreIgnoreCase(String estado);

}

package com.ApeCare_backend.util;

import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private final EstadoRepository estadoRepo;

        public DataLoader(EstadoRepository estadoRepo) {
            this.estadoRepo = estadoRepo;
        }

        @Override
        public void run(String... args) {
            if (estadoRepo.count() == 0) {
                Estado estado = new Estado();
                estado.setNombre(EstadoConst.ACTIVO);
                estado.setDescripcion("Registro activo");
                estadoRepo.save(estado);

                Estado inactivo = new Estado();
                inactivo.setNombre(EstadoConst.INACTIVO);
                inactivo.setDescripcion("Registro inactivo");
                estadoRepo.save(inactivo);

                Estado eliminado = new Estado();
                eliminado.setNombre(EstadoConst.ELIMINADO);
                eliminado.setDescripcion("Registro eliminado");
                estadoRepo.save(eliminado);

            }
        }
}

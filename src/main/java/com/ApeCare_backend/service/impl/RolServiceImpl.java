package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.RolDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Rol;
import com.ApeCare_backend.mapper.RolMapper;
import com.ApeCare_backend.repository.EstadoRepository;
import com.ApeCare_backend.repository.RolRepository;
import com.ApeCare_backend.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {
    private final RolRepository rolRepo;
    private final EstadoRepository estadoRepo;

    @Override
    public RolDTO crear(RolDTO dto) {
        Estado estado = estadoRepo.findById(1L).orElseThrow();
        Rol rol = RolMapper.toEntity(dto, estado);
        return RolMapper.toDTO(rolRepo.save(rol));
    }

    @Override
    public List<RolDTO> listarActivos() {
        return rolRepo.findByEstadoNombreIgnoreCase("ACTIVO").stream()
                .map(RolMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RolDTO editar(Long id, RolDTO dto) {
        Rol rol = rolRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado"));

        rol.setNombre(dto.getNombre());
        rol.setDescripcion(dto.getDescripcion());
        rol.setFechaCreacion(LocalDateTime.now());

        return RolMapper.toDTO(rolRepo.save(rol));
    }

    @Override
    public void eliminar(Long id, Long estadoId) {
        Rol rol = rolRepo.findById(id).orElseThrow();
        Estado estado = estadoRepo.findById(estadoId).orElseThrow();
        rol.setEstado(estado);
        rolRepo.save(rol);
    }
}

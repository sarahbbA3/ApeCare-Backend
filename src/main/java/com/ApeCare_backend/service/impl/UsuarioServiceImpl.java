package com.ApeCare_backend.service.impl;

import com.ApeCare_backend.dto.UsuarioDTO;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Rol;
import com.ApeCare_backend.entity.Usuario;
import com.ApeCare_backend.mapper.UsuarioMapper;
import com.ApeCare_backend.repository.EstadoRepository;
import com.ApeCare_backend.repository.RolRepository;
import com.ApeCare_backend.repository.UsuarioRepository;
import com.ApeCare_backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepo;
    private final EstadoRepository estadoRepo;
    private final RolRepository rolRepo;

    @Override
    public UsuarioDTO crear(UsuarioDTO dto) {
        Estado estado = estadoRepo.findById(dto.getEstadoId()).orElseThrow();
        Rol rol = rolRepo.findById(dto.getRolId()).orElseThrow();
        Usuario usuario = UsuarioMapper.toEntity(dto, estado, rol);
        return UsuarioMapper.toDTO(usuarioRepo.save(usuario));
    }

    @Override
    public List<UsuarioDTO> listarActivos() {
        return usuarioRepo.findByEstadoNombreIgnoreCase("ACTIVO").stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO editar(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        usuario.setCorreo(dto.getCorreo());
        usuario.setNombre(dto.getNombre());
        usuario.setContrasena(dto.getContrasena());
        usuario.setFechaCreacion(LocalDateTime.now());

        return UsuarioMapper.toDTO(usuarioRepo.save(usuario));
    }

    @Override
    public void eliminar(Long id, Long estadoId) {
        Usuario usuario = usuarioRepo.findById(id).orElseThrow();
        Estado estado = estadoRepo.findById(estadoId).orElseThrow();
        usuario.setEstado(estado);
        usuarioRepo.save(usuario);
    }
}

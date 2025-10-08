package com.ApeCare_backend.util;
import com.ApeCare_backend.dto.MedicoDTO;
import com.ApeCare_backend.entity.Especialidad;
import com.ApeCare_backend.entity.Estado;
import com.ApeCare_backend.entity.Medico;
import com.ApeCare_backend.entity.TandaLabor;
import com.ApeCare_backend.mapper.MedicoMapper;
import com.ApeCare_backend.repository.EspecialidadRepository;
import com.ApeCare_backend.repository.EstadoRepository;
import com.ApeCare_backend.repository.MedicoRepository;
import com.ApeCare_backend.repository.TandaLaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private TandaLaborRepository tandaLaborRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private MedicoMapper medicoMapper;

    public List<MedicoDTO> listarTodos() {
        return medicoRepository.findAll()
                .stream()
                .map(medicoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MedicoDTO crear(MedicoDTO dto) {
        Especialidad especialidad = especialidadRepository.findById(dto.getEspecialidadId())
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
        TandaLabor tandaLabor = tandaLaborRepository.findById(dto.getTandaLaborId())
                .orElseThrow(() -> new RuntimeException("TandaLabor no encontrada"));
        Estado estado = estadoRepository.findById(1L) // Estado activo por defecto
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        Medico medico = medicoMapper.toEntity(dto, especialidad, tandaLabor, estado);
        Medico guardado = medicoRepository.save(medico);
        return medicoMapper.toDTO(guardado);
    }

    public MedicoDTO actualizar(Long id, MedicoDTO dto) {
        Medico existente = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        Especialidad especialidad = especialidadRepository.findById(dto.getEspecialidadId())
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
        TandaLabor tandaLabor = tandaLaborRepository.findById(dto.getTandaLaborId())
                .orElseThrow(() -> new RuntimeException("TandaLabor no encontrada"));

        existente.setNombre(dto.getNombre());
        existente.setCedula(dto.getCedula());
        existente.setEspecialidad(especialidad);
        existente.setTandaLabor(tandaLabor);

        Medico actualizado = medicoRepository.save(existente);
        return medicoMapper.toDTO(actualizado);
    }

    public void eliminar(Long id) {
        medicoRepository.deleteById(id);
    }
}
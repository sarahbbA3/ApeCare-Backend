package com.ApeCare_backend.entity;

import com.ApeCare_backend.util.TablaNombre;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = TablaNombre.Medico)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = TablaNombre.Nombre)
    private String nombre;

    @Column(name = TablaNombre.Cedula)
    private String cedula;

    @ManyToOne
    @JoinColumn(name = "EspecialidadId", foreignKey = @ForeignKey(name = "FK_Medico_Especialidad"))
    private Especialidad especialidad;

    @ManyToOne
    @JoinColumn(name = "TandaLaborId", foreignKey = @ForeignKey(name = "FK_Medico_TandaLabor"))
    private TandaLabor tandaLabor;

    @ManyToOne
    @JoinColumn(name = "EstadoId", foreignKey = @ForeignKey(name = "FK_Medico_Estado"))
    private Estado estado;

    @CreationTimestamp
    @Column(name = TablaNombre.FechaCreacion, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaCreacion;

}
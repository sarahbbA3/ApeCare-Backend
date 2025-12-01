package com.ApeCare_backend.entity;

import com.ApeCare_backend.util.TablaNombre;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = TablaNombre.MedicamentoSuministrado)
@Data
@NoArgsConstructor
@AllArgsConstructor

public class MedicamentoSuministrado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = TablaNombre.CantidadSuministrada)
    private Integer cantidadSuministrada;

    @ManyToOne
    @JoinColumn(name = "MedicamentoId", foreignKey = @ForeignKey(name = "FK_MedicamentoSuministrado_Medicamento"))
    private Medicamento medicamento;

    @ManyToOne
    @JoinColumn(name = "VisitaId", foreignKey = @ForeignKey(name = "FK_MedicamentoSuministrado_Visita"))
    private RegistroVisita visita;

    @ManyToOne
    @JoinColumn(name = "EstadoId", foreignKey = @ForeignKey(name = "FK_MedicamentoSuministrado_Estado"))
    private Estado estado;

    @CreationTimestamp
    @Column(name = TablaNombre.FechaCreacion, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = TablaNombre.FechaActualizacion, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaActualizacion;

}

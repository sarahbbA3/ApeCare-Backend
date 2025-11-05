package com.ApeCare_backend.entity;

import com.ApeCare_backend.util.TablaNombre;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = TablaNombre.Medicamento)
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = TablaNombre.Descripcion)
    private String descripcion;

    @Column(name = TablaNombre.Dosis)
    private String dosis;

    @Column(name = TablaNombre.CantidadDisponible)
    private Integer cantidadDisponible;

    @Column(name = TablaNombre.FechaVencimiento)
    private LocalDate fechaVencimiento;

    @ManyToOne
    @JoinColumn(name = "TipoFarmacoId", foreignKey = @ForeignKey(name = "FK_Medicamento_TipoFarmaco"))
    private TipoFarmaco tipoFarmaco;

    @ManyToOne
    @JoinColumn(name = "UbicacionId", foreignKey = @ForeignKey(name = "FK_Medicamento_Ubicacion"))
    private Ubicacion ubicacion;

    @ManyToOne
    @JoinColumn(name = "MarcaId", foreignKey = @ForeignKey(name = "FK_Medicamento_Marca"))
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "EstadoId", foreignKey = @ForeignKey(name = "FK_Medicamento_Estado"))
    private Estado estado;

    @CreationTimestamp
    @Column(name = TablaNombre.FechaCreacion, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaActualizacion;

}

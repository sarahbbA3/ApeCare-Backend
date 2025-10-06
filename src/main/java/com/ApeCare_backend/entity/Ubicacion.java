package com.ApeCare_backend.entity;

import com.ApeCare_backend.util.TablaNombre;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = TablaNombre.Ubicacion)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = TablaNombre.Estante)
    private String estante;

    @Column(name = TablaNombre.Tramo)
    private String tramo;

    @Column(name = TablaNombre.Celda)
    private String celda;

    @ManyToOne
    @JoinColumn(name = "EstadoId", foreignKey = @ForeignKey(name = "FK_Ubicacion_Estado"))
    private Estado estado;

    @CreationTimestamp
    @Column(name = TablaNombre.FechaCreacion, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaCreacion;

}

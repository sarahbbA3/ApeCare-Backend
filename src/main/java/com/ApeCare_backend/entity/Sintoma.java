package com.ApeCare_backend.entity;

import com.ApeCare_backend.util.TablaNombre;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = TablaNombre.Sintoma)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sintoma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = TablaNombre.Nombre)
    private String nombre;

    @Column(name = TablaNombre.Descripcion)
    private String descripcion;

    @OneToMany(mappedBy = "sintoma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistroVisitaSintoma> visitas;

    @ManyToOne
    @JoinColumn(name = "EstadoId", foreignKey = @ForeignKey(name = "FK_Sintoma_Estado"))
    private Estado estado;

    @CreationTimestamp
    @Column(name = TablaNombre.FechaCreacion, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaCreacion;

}

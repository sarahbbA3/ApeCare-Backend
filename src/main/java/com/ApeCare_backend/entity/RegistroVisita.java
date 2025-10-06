package com.ApeCare_backend.entity;

import com.ApeCare_backend.util.TablaNombre;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = TablaNombre.RegistroVisita)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroVisita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = TablaNombre.FechaVisita, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaVisita;

    @Column(name = TablaNombre.HoraVisita)
    private LocalTime horaVisita;

    @Column(name = TablaNombre.Recomendaciones)
    private String recomendaciones;

    @OneToMany(mappedBy = "registroVisita", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistroVisitaSintoma> sintomas;

    @ManyToOne
    @JoinColumn(name = "PacienteId", foreignKey = @ForeignKey(name = "FK_RegistroVisita_Paciente"))
    private Paciente paciente;

    @OneToMany(mappedBy = "visita", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicamentoSuministrado> medicamentosEntregados;

    @ManyToOne
    @JoinColumn(name = "MedicoId", foreignKey = @ForeignKey(name = "FK_RegistroVisita_Medico"))
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "EstadoId",foreignKey = @ForeignKey(name = "FK_RegistroVisita_Estado"))
    private Estado estado;

    @CreationTimestamp
    @Column(name = TablaNombre.FechaCreacion, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaCreacion;

}
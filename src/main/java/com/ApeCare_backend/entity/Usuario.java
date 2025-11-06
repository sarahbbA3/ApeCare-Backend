package com.ApeCare_backend.entity;

import com.ApeCare_backend.util.TablaNombre;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = TablaNombre.Usuario)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = TablaNombre.Correo, unique = true)
    private String correo;

    @Column(name = TablaNombre.Nombre)
    private String nombre;

    @Column(name = TablaNombre.Contrasena)
    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "EstadoId", foreignKey = @ForeignKey(name = "FK_Usuario_Estado"))
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "RolId", foreignKey = @ForeignKey(name = "FK_Usuario_Rol"))
    private Rol rol;

    @CreationTimestamp
    @Column(name = TablaNombre.FechaCreacion, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = TablaNombre.FechaActualizacion, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaActualizacion;

}

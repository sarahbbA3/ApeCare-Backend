package com.ApeCare_backend.entity;

import com.ApeCare_backend.util.TablaNombre;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = TablaNombre.Estado)
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = TablaNombre.Nombre)
    private String nombre;

    @Column(name = TablaNombre.Descripcion)
    private String descripcion;

}

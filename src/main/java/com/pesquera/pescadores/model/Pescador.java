package com.pesquera.pescadores.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pescadores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pescador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(unique = true, nullable = false)
    private String rut;

    @Column(unique = true, nullable = false)
    private String licencia;

    @Column(nullable = false)
    private String sindicato;

    @Column(nullable = false)
    private Boolean activo;
}
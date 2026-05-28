package com.pesquera.pescadores.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PescadorRequestDTO(

    @NotBlank(message = "El nombre es obligatorio")
    String nombre,

    @NotBlank(message = "El apellido es obligatorio")
    String apellido,

    @NotBlank(message = "El RUT es obligatorio")
    String rut,

    @NotBlank(message = "La licencia es obligatoria")
    String licencia,

    @NotBlank(message = "El sindicato es obligatorio")
    String sindicato,

    @NotNull(message = "El estado activo es obligatorio")
    Boolean activo
) {}
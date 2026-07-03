package com.pesquera.pescadores.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PescadorRequestDTO(

    @NotBlank(message = "El nombre es obligatorio")
    String nombre,

    @NotBlank(message = "El apellido es obligatorio")
    String apellido,

    @NotBlank(message = "El RUT es obligatorio")
    @Pattern(
        regexp = "^\\d{7,8}-[\\dkK]$",
        message = "El RUT debe tener el formato 12345678-9"
    )
    String rut,

    @NotBlank(message = "La licencia es obligatoria")
    String licencia,

    @NotBlank(message = "El sindicato es obligatorio")
    String sindicato,

    @NotNull(message = "El estado activo es obligatorio")
    Boolean activo
) {}
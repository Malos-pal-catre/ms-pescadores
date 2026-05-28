package com.pesquera.pescadores.dto;


public record PescadorResponseDTO(
    Long id,
    String nombre,
    String apellido,
    String rut,
    String licencia,
    String sindicato,
    Boolean activo
) {}
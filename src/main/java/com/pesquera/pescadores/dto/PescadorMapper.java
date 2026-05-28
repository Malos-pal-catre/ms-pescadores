package com.pesquera.pescadores.dto;

import com.pesquera.pescadores.model.Pescador;

public class PescadorMapper {

    public static PescadorResponseDTO toDTO(Pescador p) {
        return new PescadorResponseDTO(
            p.getId(),
            p.getNombre(),
            p.getApellido(),
            p.getRut(),
            p.getLicencia(),
            p.getSindicato(),
            p.getActivo()
        );
    }

    public static Pescador toEntity(PescadorRequestDTO dto) {
        return Pescador.builder()
            .nombre(dto.nombre())
            .apellido(dto.apellido())
            .rut(dto.rut())
            .licencia(dto.licencia())
            .sindicato(dto.sindicato())
            .activo(dto.activo())
            .build();
    }
}
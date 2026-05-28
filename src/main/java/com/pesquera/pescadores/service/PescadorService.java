package com.pesquera.pescadores.service;


import com.pesquera.pescadores.dto.*;
import com.pesquera.pescadores.exception.RecursoNoEncontradoException;
import com.pesquera.pescadores.model.Pescador;
import com.pesquera.pescadores.repository.PescadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PescadorService {

    private final PescadorRepository pescadorRepository;

    public List<PescadorResponseDTO> listarTodos() {
        return pescadorRepository.findAll()
            .stream()
            .map(PescadorMapper::toDTO)
            .toList();
    }

    public PescadorResponseDTO buscarPorId(Long id) {
        Pescador p = pescadorRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Pescador no encontrado con id: " + id));
        return PescadorMapper.toDTO(p);
    }

    public PescadorResponseDTO buscarPorRut(String rut) {
        Pescador p = pescadorRepository.findByRut(rut)
            .orElseThrow(() -> new RecursoNoEncontradoException("Pescador no encontrado con RUT: " + rut));
        return PescadorMapper.toDTO(p);
    }

    public List<PescadorResponseDTO> listarActivos() {
        return pescadorRepository.findByActivoTrue()
            .stream()
            .map(PescadorMapper::toDTO)
            .toList();
    }

    public List<PescadorResponseDTO> buscarPorNombre(String nombre) {
        return pescadorRepository.buscarPorNombre(nombre)
            .stream()
            .map(PescadorMapper::toDTO)
            .toList();
    }

    public PescadorResponseDTO registrar(PescadorRequestDTO dto) {
        if (pescadorRepository.findByRut(dto.rut()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un pescador con el RUT: " + dto.rut());
        }
        Pescador nuevo = PescadorMapper.toEntity(dto);
        return PescadorMapper.toDTO(pescadorRepository.save(nuevo));
    }

    public PescadorResponseDTO actualizar(Long id, PescadorRequestDTO dto) {
        Pescador existente = pescadorRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Pescador no encontrado con id: " + id));
        existente.setNombre(dto.nombre());
        existente.setApellido(dto.apellido());
        existente.setRut(dto.rut());
        existente.setLicencia(dto.licencia());
        existente.setSindicato(dto.sindicato());
        existente.setActivo(dto.activo());
        return PescadorMapper.toDTO(pescadorRepository.save(existente));
    }

    public void eliminar(Long id) {
        if (!pescadorRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Pescador no encontrado con id: " + id);
        }
        pescadorRepository.deleteById(id);
    }
}
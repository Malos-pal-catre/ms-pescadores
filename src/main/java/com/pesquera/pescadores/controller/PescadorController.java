package com.pesquera.pescadores.controller;


import com.pesquera.pescadores.dto.*;
import com.pesquera.pescadores.service.PescadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pescadores")
@RequiredArgsConstructor
public class PescadorController {

    private final PescadorService pescadorService;

    @GetMapping
    public ResponseEntity<List<PescadorResponseDTO>> listarTodos() {
        return ResponseEntity.ok(pescadorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PescadorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pescadorService.buscarPorId(id));
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<PescadorResponseDTO> buscarPorRut(@PathVariable String rut) {
        return ResponseEntity.ok(pescadorService.buscarPorRut(rut));
    }

    @GetMapping("/activos")
    public ResponseEntity<List<PescadorResponseDTO>> listarActivos() {
        return ResponseEntity.ok(pescadorService.listarActivos());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<PescadorResponseDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(pescadorService.buscarPorNombre(nombre));
    }

    @PostMapping
    public ResponseEntity<PescadorResponseDTO> registrar(@RequestBody @Valid PescadorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pescadorService.registrar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PescadorResponseDTO> actualizar(@PathVariable Long id,
                                                          @RequestBody @Valid PescadorRequestDTO dto) {
        return ResponseEntity.ok(pescadorService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pescadorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
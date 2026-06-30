package com.pesquera.pescadores.controller;

import com.pesquera.pescadores.dto.*;
import com.pesquera.pescadores.service.PescadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pescadores")
@RequiredArgsConstructor
@Tag(name = "Pescadores", description = "Gestión de pescadores artesanales registrados en la Caleta Lo Abarca")
public class PescadorController {

    private final PescadorService pescadorService;

    @Operation(summary = "Listar todos los pescadores", description = "Retorna el listado completo de pescadores registrados.")
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    @GetMapping
    public ResponseEntity<List<PescadorResponseDTO>> listarTodos() {
        return ResponseEntity.ok(pescadorService.listarTodos());
    }

    @Operation(summary = "Buscar pescador por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pescador encontrado"),
        @ApiResponse(responseCode = "404", description = "No existe un pescador con el ID indicado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PescadorResponseDTO> buscarPorId(
            @Parameter(description = "ID del pescador", example = "3") @PathVariable Long id) {
        return ResponseEntity.ok(pescadorService.buscarPorId(id));
    }

    @Operation(summary = "Buscar pescador por RUT", description = "Búsqueda exacta por RUT, usada principalmente por ms-auth y ms-pagos.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pescador encontrado"),
        @ApiResponse(responseCode = "404", description = "No existe un pescador con ese RUT", content = @Content)
    })
    @GetMapping("/rut/{rut}")
    public ResponseEntity<PescadorResponseDTO> buscarPorRut(
            @Parameter(description = "RUT del pescador", example = "12345678-9") @PathVariable String rut) {
        return ResponseEntity.ok(pescadorService.buscarPorRut(rut));
    }

    @Operation(summary = "Listar pescadores activos", description = "Retorna solo los pescadores cuyo estado está habilitado para operar.")
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    @GetMapping("/activos")
    public ResponseEntity<List<PescadorResponseDTO>> listarActivos() {
        return ResponseEntity.ok(pescadorService.listarActivos());
    }

    @Operation(summary = "Buscar pescadores por nombre", description = "Búsqueda parcial e insensible a mayúsculas por nombre.")
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    @GetMapping("/buscar")
    public ResponseEntity<List<PescadorResponseDTO>> buscarPorNombre(
            @Parameter(description = "Texto a buscar dentro del nombre", example = "Pedro") @RequestParam String nombre) {
        return ResponseEntity.ok(pescadorService.buscarPorNombre(nombre));
    }

    @Operation(summary = "Registrar un nuevo pescador", description = "Crea un nuevo pescador artesanal en el sistema.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        required = true,
        content = @Content(
            schema = @Schema(implementation = PescadorRequestDTO.class),
            examples = @ExampleObject(
                name = "Pescador de ejemplo",
                value = """
                {
                  "nombre": "Pedro González",
                  "rut": "12345678-9",
                  "telefono": "+56912345678",
                  "caletaId": 1
                }
                """
            )
        )
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Pescador registrado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos (ej: RUT duplicado o mal formado)", content = @Content)
    })
    @PostMapping
    public ResponseEntity<PescadorResponseDTO> registrar(@RequestBody @Valid PescadorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pescadorService.registrar(dto));
    }

    @Operation(summary = "Actualizar un pescador existente")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        required = true,
        content = @Content(
            schema = @Schema(implementation = PescadorRequestDTO.class),
            examples = @ExampleObject(
                name = "Actualización de ejemplo",
                value = """
                {
                  "nombre": "Pedro González Soto",
                  "rut": "12345678-9",
                  "telefono": "+56987654321",
                  "caletaId": 1
                }
                """
            )
        )
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pescador actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Pescador no encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<PescadorResponseDTO> actualizar(
            @Parameter(description = "ID del pescador", example = "3") @PathVariable Long id,
            @RequestBody @Valid PescadorRequestDTO dto) {
        return ResponseEntity.ok(pescadorService.actualizar(id, dto));
    }

    @Operation(summary = "Eliminar un pescador")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Pescador eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Pescador no encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@Parameter(description = "ID del pescador", example = "3") @PathVariable Long id) {
        pescadorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
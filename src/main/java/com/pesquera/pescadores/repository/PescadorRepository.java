package com.pesquera.pescadores.repository;

import com.pesquera.pescadores.model.Pescador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PescadorRepository extends JpaRepository<Pescador, Long> {

    // Query Method
    Optional<Pescador> findByRut(String rut);
    Optional<Pescador> findByLicencia(String licencia);
    List<Pescador> findBySindicato(String sindicato);
    List<Pescador> findByActivoTrue();

    // Custom Query
    @Query("SELECT p FROM Pescador p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Pescador> buscarPorNombre(@Param("nombre") String nombre);

    @Query(value = "SELECT * FROM pescadores WHERE sindicato = :sindicato AND activo = true", nativeQuery = true)
    List<Pescador> pescadoresActivosPorSindicato(@Param("sindicato") String sindicato);
}
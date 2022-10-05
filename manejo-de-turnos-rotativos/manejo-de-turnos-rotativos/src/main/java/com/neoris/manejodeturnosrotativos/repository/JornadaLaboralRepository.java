package com.neoris.manejodeturnosrotativos.repository;

import com.neoris.manejodeturnosrotativos.entity.JornadaLaboral;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JornadaLaboralRepository extends JpaRepository<JornadaLaboral, Long> {
    boolean existsByNombre(String nombre);
}

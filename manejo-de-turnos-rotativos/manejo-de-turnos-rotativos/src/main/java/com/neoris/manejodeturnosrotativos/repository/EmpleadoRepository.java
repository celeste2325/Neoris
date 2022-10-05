package com.neoris.manejodeturnosrotativos.repository;

import com.neoris.manejodeturnosrotativos.entity.Empleado;
import com.neoris.manejodeturnosrotativos.entity.EmpleadoPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, EmpleadoPk> {
}

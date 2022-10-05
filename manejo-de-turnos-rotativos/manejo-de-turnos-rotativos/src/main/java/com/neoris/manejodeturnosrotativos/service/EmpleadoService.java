package com.neoris.manejodeturnosrotativos.service;

import com.neoris.manejodeturnosrotativos.entity.Empleado;
import com.neoris.manejodeturnosrotativos.exceptions.EmpleadoExistente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoService{
    Empleado addEmpleado(Empleado newEmpleado) throws EmpleadoExistente;
}

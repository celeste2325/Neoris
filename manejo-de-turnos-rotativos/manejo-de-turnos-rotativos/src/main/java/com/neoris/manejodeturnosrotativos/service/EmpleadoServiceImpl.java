package com.neoris.manejodeturnosrotativos.service;

import com.neoris.manejodeturnosrotativos.entity.Empleado;
import com.neoris.manejodeturnosrotativos.exceptions.EmpleadoExistente;
import com.neoris.manejodeturnosrotativos.repository.EmpleadoRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public Empleado addEmpleado(Empleado newEmpleado) throws EmpleadoExistente {
        if (!empleadoRepository.existsById(newEmpleado.getTipo_documento())){
            return this.empleadoRepository.save(newEmpleado);
        } else throw new EmpleadoExistente("El empleado ya fue cargado");
    }
}

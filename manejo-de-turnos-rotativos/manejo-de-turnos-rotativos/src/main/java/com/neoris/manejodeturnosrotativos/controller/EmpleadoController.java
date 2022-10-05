package com.neoris.manejodeturnosrotativos.controller;

import com.neoris.manejodeturnosrotativos.entity.Empleado;
import com.neoris.manejodeturnosrotativos.exceptions.EmpleadoExistente;
import com.neoris.manejodeturnosrotativos.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiManejoTurnosRotativos")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @PostMapping("/empleados")
    public ResponseEntity addEmpleado(@RequestBody Empleado newEmpleado){
        try {
            return new ResponseEntity<>(this.empleadoService.addEmpleado(newEmpleado),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

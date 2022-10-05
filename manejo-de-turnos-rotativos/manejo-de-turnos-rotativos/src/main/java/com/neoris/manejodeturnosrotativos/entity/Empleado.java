package com.neoris.manejodeturnosrotativos.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "empleados")
@Setter
@Getter
public class Empleado{
    @EmbeddedId
    private EmpleadoPk tipo_documento; //clave compuesta

    @Basic
    @Column(name = "nombreYapellido")
    @NotNull
    @NotBlank
    private String nombreYapellido;

    //queda abierto a que se puedan modificar las horas permitidas para trabajar de acuerdo al requerimiento de la organizacion
    @Basic
    @Column(name = "minimoDeHorasPermitidasSemanal")
    private int minimoDeHorasPermitidasSemanal = 30;

    @Basic
    @Column(name = "maximoDeHorasPermitidasSemanal")
    private int maximoDeHorasPermitidasSemanal = 48;

    @Basic
    @Column(name = "maximoDeHorasPermitidasDiario")
    private int maximoDeHorasPermitidasDiario = 12;
}

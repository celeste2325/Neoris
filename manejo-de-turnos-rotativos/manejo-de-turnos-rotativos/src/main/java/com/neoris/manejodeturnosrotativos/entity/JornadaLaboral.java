package com.neoris.manejodeturnosrotativos.entity;
import lombok.Getter;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "jornadasLaborales")
@Getter
public class JornadaLaboral {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Basic
    @Column(name = "nombre") //Se asume que el nombre siempre sera guardado en minuscula siempre igual y de la forma correcta, no hay error de tipeo
    @NotNull
    @NotBlank
    private String nombre;

    /*con las siguientes columnas queda abierto a que se puedan asignar la cantidad de hora permitida para cada jornada
    tambien la cantidad de empleados permitidos por jornada,en caso de que para otra organización una jornada normal sea de 10hs por ejm.
    Se van a respetar las validaciones de estos horarios antes de asignar la jornada al empleado*/
    @Basic
    @Column(name = "minHorasPermitidasPorSemana")
    @NotNull
    @Min(1)
    private Integer minHorasPermitidasPorSemana;

    @Basic
    @Column(name = "maxHorasPermitidasPorSemana")
    @NotNull
    @Min(1) //no admite numeros negativos
    private Integer maxHorasPermitidasPorSemana;

    @Basic
    @Column(name = "maximoDeEmpleadosPorDia")
    @Min(1)
    private Integer maximoDeEmpleadosPorDia = 2; /* 2 empleados por jornada por defecto, en caso de que no se especifique al crear la jornada
                                                  en caso de jornada no laborable asigna cuantos empleados pueden estar de vacaciones/libre por dia*/

    @Basic
    @Column(name = "isLaborable")
    @NotNull
    private Boolean isLaborable; /*para definir si el tipo de jornada es o no laborable, caso de vacaciones es no laborable.
    utilizado para calcular la cantidad de horas laboradas del empleado*/
}

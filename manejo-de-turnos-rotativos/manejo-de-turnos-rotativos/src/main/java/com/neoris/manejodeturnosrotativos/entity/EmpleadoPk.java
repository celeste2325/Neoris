package com.neoris.manejodeturnosrotativos.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class EmpleadoPk implements Serializable {
    private static final long serialVersionUID = -2949839139887898318L;
    private String tipoDocumento;
    private String nroDocumento;
}

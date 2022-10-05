package com.neoris.manejodeturnosrotativos.service;

import com.neoris.manejodeturnosrotativos.entity.JornadaLaboral;
import com.neoris.manejodeturnosrotativos.exceptions.JornadaLaboralExistente;

public interface JornadaLaboralService {
    JornadaLaboral addJornadaLaboral(JornadaLaboral newJornadaLaboral) throws JornadaLaboralExistente;
}

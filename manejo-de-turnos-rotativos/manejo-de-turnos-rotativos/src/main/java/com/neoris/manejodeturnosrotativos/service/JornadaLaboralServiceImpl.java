package com.neoris.manejodeturnosrotativos.service;

import com.neoris.manejodeturnosrotativos.entity.JornadaLaboral;
import com.neoris.manejodeturnosrotativos.exceptions.JornadaLaboralExistente;
import com.neoris.manejodeturnosrotativos.repository.JornadaLaboralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JornadaLaboralServiceImpl implements JornadaLaboralService{
    @Autowired
    JornadaLaboralRepository jornadaLaboralRepository;

    @Override
    public JornadaLaboral addJornadaLaboral(JornadaLaboral newJornadaLaboral) throws JornadaLaboralExistente {
        if (!this.jornadaLaboralRepository.existsByNombre(newJornadaLaboral.getNombre())) { //asegura que exista una sola jornada por tipo
            return this.jornadaLaboralRepository.save(newJornadaLaboral);
        } else throw new JornadaLaboralExistente("La jornada laboral que intenta crear ya fue creada anteriormente");
    }
}

package com.squad12.cargaHoras.Service;

import com.squad12.cargaHoras.Model.Horas;
import com.squad12.cargaHoras.Repository.HorasRepository;
import com.squad12.cargaHoras.exceptions.TooManyHoursException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HorasService {

    private Double maxHoras = 10.0;

    @Autowired
    private HorasRepository horasRepository;

    public Horas createHoras(Horas horas) {
        if (this.asignacionEsValida(horas)) {
            return horasRepository.save(horas);
        }
        else {
            throw new TooManyHoursException("Demasiadas horas cargadas");
        }

    }


    public Collection<Horas> getHoras() {
        return horasRepository.findAll();
    }

    public Optional<Horas> findById(Long id) {
        return horasRepository.findById(id);
    }


    public void save(Horas horas) {
        if (asignacionEsValida(horas)) {
            horasRepository.save(horas);
        } else {
            throw new TooManyHoursException("Demasiadas horas cargadas");
        }
    }

    public Collection<Horas> getHorasByRecurso(String recurso) {
        Collection<Horas> horas = horasRepository.findAll();
        ArrayList<Horas> horasRecurso = new ArrayList<Horas>();

        for (Horas x : horas) {
            if (Objects.equals(x.getRecurso(), recurso)) {
                System.out.println(x.getRecurso());
                System.out.println(recurso);
                horasRecurso.add(x);
            }
        }
        return horasRecurso;
    }

    public void deleteById(Long id) {
        horasRepository.deleteById(id);
    }


    private boolean asignacionEsValida(Horas horas) {
        return horas.getHoras() < maxHoras;
    }
}

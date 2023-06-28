package com.squad12.cargaHoras.Service;

import com.squad12.cargaHoras.Model.Horas;
import com.squad12.cargaHoras.Model.Tarea;
import com.squad12.cargaHoras.Repository.HorasRepository;
import com.squad12.cargaHoras.exceptions.TooManyHoursException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Service
public class HorasService {

    private Double maxHoras = 10.0;
    private String proyectosURL = "https://aninfo-backend-proyectos.onrender.com";

    @Autowired
    private HorasRepository horasRepository;
    RestTemplate restTemplate = new RestTemplate();

    public Horas createHoras(Horas horas) {

        if (this.asignacionEsValida(horas)) {
            return horasRepository.save(horas);
        } else {
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

    public Collection<Horas> getHorasByRecurso(Long recurso) {
        Collection<Horas> horas = horasRepository.findAll();
        ArrayList<Horas> horasRecurso = new ArrayList<Horas>();

        for (Horas x : horas) {
            if (Objects.equals(x.getRecurso(), recurso)) {
                horasRecurso.add(x);
            }
        }
        return horasRecurso;
    }

    public Double getHorasByProyecto(Long proyecto) {
        Collection<Horas> horas = horasRepository.findAll();

        Double total;
        total = 0.0;
        for (Horas x : horas) {
            if (Objects.equals(x.getProyecto(), proyecto)) {
                total += x.getHoras();
            }
        }
        return total;
    }

    public void deleteById(Long id) {
        horasRepository.deleteById(id);
    }


    private boolean asignacionEsValida(Horas horas) {
        return true;
        /*
        ResponseEntity<Tarea> responseTarea = restTemplate.getForEntity(
                proyectosURL + "/tasks/" + horas.getTarea(),
                Tarea.class
        );
        if (responseTarea.getBody() == null) {
            return false;
        }
        Tarea t = responseTarea.getBody();

        if (!Objects.equals(t.resourceId, horas.getRecurso())) {
            return false;
        }
        if (!Objects.equals(t.projectId, horas.getProyecto())) {
            return false;
        }

        return horas.getHoras() <= maxHoras;
        */

    }

    public Double getHorasByTarea(Long proyecto) {
        Collection<Horas> horas = horasRepository.findAll();

        Double total;
        total = 0.0;
        for (Horas x : horas) {
            if (Objects.equals(x.getTarea(), proyecto)) {
                total += x.getHoras();
            }
        }
        return total;
    }
}

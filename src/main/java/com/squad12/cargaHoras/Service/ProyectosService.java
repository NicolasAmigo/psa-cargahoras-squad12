package com.squad12.cargaHoras.Service;

import com.squad12.cargaHoras.Model.Proyecto;
import com.squad12.cargaHoras.Model.ProyectoSimple;
import com.squad12.cargaHoras.Model.Tarea;
import com.squad12.cargaHoras.Model.TareaSimple;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ProyectosService {

    String proyectosURL = "https://aninfo-backend-proyectos.onrender.com";

    RestTemplate restTemplate = new RestTemplate();
    public Collection<ProyectoSimple> getProyectoByRecurso(Long recurso) {
        ResponseEntity<Proyecto[]> response = restTemplate.getForEntity(
                proyectosURL + "/projects",
                Proyecto[].class
        );
        if (response.getBody() == null) {
            return new ArrayList<>();
        }
        List<Proyecto> proyectos = Arrays.asList(response.getBody());
        ArrayList<ProyectoSimple> proyectosSimples = new ArrayList<>();

        for (Proyecto p : proyectos) {
            proyectosSimples.add(new ProyectoSimple(p.id, p.name));
        }

        for (ProyectoSimple p : proyectosSimples) {
            ResponseEntity<Tarea[]> responseTarea = restTemplate.getForEntity(
                    proyectosURL + "/projects/" + String.valueOf(p.id) + "/tasks",
                    Tarea[].class
            );
            if (responseTarea.getBody() == null) {
                continue;
            }
            List<Tarea> tareas = Arrays.asList(responseTarea.getBody());
            ArrayList<TareaSimple> tareasSimples = new ArrayList<>();
            for (Tarea t: tareas) {
                if (Objects.equals(t.resourceId, recurso)) {
                    tareasSimples.add(new TareaSimple(t.id,t.name));
                }
            }
            p.setTareas(tareasSimples);
        }

        proyectosSimples.removeIf(p -> p.getTareas().isEmpty());

        return proyectosSimples;

    }

    public Collection<ProyectoSimple> getProyectos() {
        /*
        ResponseEntity<Proyecto[]> response = restTemplate.getForEntity(
                proyectosURL + "/projects",
                Proyecto[].class
        );
        if (response.getBody() == null) {
            return new ArrayList<>();
        }
        List<Proyecto> proyectos = Arrays.asList(response.getBody());
        ArrayList<ProyectoSimple> proyectosSimples = new ArrayList<>();

        for (Proyecto p : proyectos) {
            proyectosSimples.add(new ProyectoSimple(p.id, p.name));
        }

        for (ProyectoSimple p : proyectosSimples) {
            ResponseEntity<Tarea[]> responseTarea = restTemplate.getForEntity(
                    proyectosURL + "/projects/" + String.valueOf(p.id) + "/tasks",
                    Tarea[].class
            );
            if (responseTarea.getBody() == null) {
                continue;
            }
            List<Tarea> tareas = Arrays.asList(responseTarea.getBody());
            ArrayList<TareaSimple> tareasSimples = new ArrayList<>();
            for (Tarea t: tareas) {
                tareasSimples.add(new TareaSimple(t.id,t.name));
            }
            p.setTareas(tareasSimples);
        }

        proyectosSimples.removeIf(p -> p.getTareas().isEmpty());

        return proyectosSimples;
        */


        ResponseEntity<Proyecto[]> response = restTemplate.getForEntity(
                proyectosURL + "/projects",
                Proyecto[].class
        );

        List<Proyecto> proyectos = Arrays.asList(response.getBody());
        ArrayList<ProyectoSimple> proyectosSimples = new ArrayList<>();

        for (Proyecto p : proyectos) {
            proyectosSimples.add(new ProyectoSimple(p.id, p.name));
        }

        ResponseEntity<Tarea[]> responseTarea = restTemplate.getForEntity(
                proyectosURL + "/tasks",
                Tarea[].class
        );
        List<Tarea> tareas = Arrays.asList(responseTarea.getBody());
        ArrayList<TareaSimple> tareasSimples = new ArrayList<>();

        for (Tarea t : tareas) {
            for (ProyectoSimple p : proyectosSimples){
                if (Objects.equals(t.projectId, p.id)) {
                    p.getTareas().add(new TareaSimple(t.id, t.name));
                    break;
                }
            }
        }
        return proyectosSimples;
    }
}

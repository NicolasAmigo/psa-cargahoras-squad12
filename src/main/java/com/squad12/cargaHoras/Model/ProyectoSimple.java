package com.squad12.cargaHoras.Model;

import java.util.ArrayList;

public class ProyectoSimple {

    public Long id;
    public String nombre;
    public ArrayList<TareaSimple> tareas;

    public ProyectoSimple(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.tareas = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<TareaSimple> getTareas() {
        return tareas;
    }

    public void setTareas(ArrayList<TareaSimple> tareas) {
        this.tareas = tareas;
    }

}

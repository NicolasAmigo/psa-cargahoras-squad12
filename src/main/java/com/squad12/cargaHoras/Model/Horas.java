package com.squad12.cargaHoras.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "horas")
public class Horas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private Long recursoId;

    private Long proyectoId;

    private Long tareaId;

    private String fecha;

    private Double horas;


    public Horas() {}

    public Horas(Long recurso, Long proyecto, Long tarea, String fecha, Double horas) {
        this.recursoId = recurso;
        this.proyectoId = proyecto;
        this.tareaId = tarea;
        this.fecha = fecha;
        this.horas = horas;
    }

    public Long getRecurso() {
        return this.recursoId;
    }

    public void setRecurso(Long recurso) {
        this.recursoId = recurso;
    }

    public Long getProyecto() {
        return proyectoId;
    }

    public void setProyecto(Long proyecto) {
        this.proyectoId = proyecto;
    }

    public Long getTarea() {
        return tareaId;
    }

    public void setTarea(Long tarea) {
        this.tareaId = tarea;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getHoras() {
        return horas;
    }

    public void setHoras(Double horas) {
        this.horas = horas;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }


}

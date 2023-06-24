package com.squad12.cargaHoras.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "horas")
public class Horas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String recurso;

    private String proyecto;

    private String tarea;

    private String fecha;

    private Double horas;


    public Horas() {}

    public Horas(String recurso, String proyecto, String tarea, String fecha, Double horas) {
        this.recurso = recurso;
        this.proyecto = proyecto;
        this.tarea = tarea;
        this.fecha = fecha;
        this.horas = horas;
    }

    public String getRecurso() {
        return this.recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
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

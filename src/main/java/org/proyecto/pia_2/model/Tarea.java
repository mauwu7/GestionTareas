package org.proyecto.pia_2.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


//Puede que haya que implementar validacion por grupos
@MappedSuperclass
//@Validated({BasicInfo.class,EditedInfo.class})
public abstract class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tarea_id;

    //@NotNull(groups = BasicInfo.class)
    @NotNull
    private String descripcion;

    //@NotNull(groups = BasicInfo.class)
    //@NotNull(groups = EditedInfo.class)
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaVencimiento;

    //@NotNull(groups = BasicInfo.class)
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaCreacion;

    //@NotNull(groups = BasicInfo.class)
    //@NotNull(groups = EditedInfo.class)
    @Min(1)
    @NotNull
    private Integer prioridad;

    //@NotNull(groups = BasicInfo.class)
    //@NotNull(groups = EditedInfo.class)
    //@NotNull
    private String estado; // PENDIENTE, EN_PROGRESO, COMPLETADA

    @ElementCollection //Administracion, contabilidad etc
    //@NotNull(groups = EditedInfo.class)
    //@NotNull(groups = BasicInfo.class)
    private List<String> etiquetas = new ArrayList<>();

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    private Equipo equipo;
    */

    public Tarea(){}

    /*
    public Tarea(String descripcion, LocalDate fechaVencimiento, LocalDate fechaCreacion, Integer prioridad, String estado, List<String> etiquetas) {
        this.tarea_id = tarea_id;
        this.descripcion = descripcion;
        this.fechaVencimiento = fechaVencimiento;
        this.fechaCreacion = fechaCreacion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.etiquetas = etiquetas;
    }
     */
    public Long getTarea_id() {
        return tarea_id;
    }

    public void setTarea_id(Long tarea_id) {
        this.tarea_id = tarea_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<String> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<String> etiquetas) {
        this.etiquetas = etiquetas;
    }

    //Se maneja en los servicios
    // void mostrarTarea(){};

}

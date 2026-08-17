package org.example.backend.DTO;

import org.example.backend.model.Tarea;

import java.util.List;

public class TareaDTO {

    private List<Tarea> planificadorTareas;
    private List<Tarea> tareasOrden;

    public TareaDTO(){}


    public List<Tarea> getPlanificadorTareas() {
        return planificadorTareas;
    }

    public void setPlanificadorTareas(List<Tarea> planificadorTareas) {
        this.planificadorTareas = planificadorTareas;
    }

    public List<Tarea> getTareasOrden() {
        return tareasOrden;
    }

    public void setTareasOrden(List<Tarea> tareasOrden) {
        this.tareasOrden = tareasOrden;
    }
}

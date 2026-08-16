package org.example.backend.DTO;

import org.example.backend.model.Tarea;

import java.util.List;

public class TareaDTO {

    private List<Tarea> tareasPl;
    private List<Tarea> tareasOrden;

    public TareaDTO(){}


    public List<Tarea> getTareasPl() {
        return tareasPl;
    }

    public void setTareasPl(List<Tarea> tareasPl) {
        this.tareasPl = tareasPl;
    }

    public List<Tarea> getTareasOrden() {
        return tareasOrden;
    }

    public void setTareasOrden(List<Tarea> tareasOrden) {
        this.tareasOrden = tareasOrden;
    }
}

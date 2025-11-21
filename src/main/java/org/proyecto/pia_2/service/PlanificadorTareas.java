package org.proyecto.pia_2.service;
import org.proyecto.pia_2.model.TareaIndividual;
import java.util.Comparator;

public class PlanificadorTareas implements Comparator<TareaIndividual> {

    public PlanificadorTareas() {}

    @Override
    public int compare(TareaIndividual o1, TareaIndividual o2) {
        if(o1.getPrioridad() > o2.getPrioridad()){
            return 1;
        }
        else if(o1.getPrioridad() < o2.getPrioridad()){
            return -1;
        }
        else{
            if(o1.getFechaVencimiento().compareTo(o2.getFechaVencimiento()) > 0){
                 return 1;
            }
            else if (o1.getFechaVencimiento().compareTo(o2.getFechaVencimiento()) < 0){
                 return -1;
            }
            else{
                return 0;
            }
        }
    }

}

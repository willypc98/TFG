package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class BancoDeTrabajo extends RecursoWeb{

    private String descripcionBanco;
    private ArrayList<LocalDateTime> listaDisponibilidadBanco;
    private ArrayList<RecursosBancoDeTrabajo> listaRecursosBanco;

    public BancoDeTrabajo(){
        super();
    }

    public BancoDeTrabajo(int id, String url, String descripcionBanco) {
        super(id, url);
        this.descripcionBanco=descripcionBanco;
    }


    public String getDescripcionBanco() {
        return descripcionBanco;
    }

    public void setDescripcionBanco(String desccripcionBanco) {
        this.descripcionBanco = desccripcionBanco;
    }

    public ArrayList<RecursosBancoDeTrabajo> getListaRecursosBanco() {
        return listaRecursosBanco;
    }

    public void setListaRecursosBanco(ArrayList<RecursosBancoDeTrabajo> listaRecursosBanco) {
        this.listaRecursosBanco = listaRecursosBanco;
    }

    public ArrayList<LocalDateTime> getListaDisponibilidadBanco() {
        return listaDisponibilidadBanco;
    }

    public void setListaDisponibilidadBanco(ArrayList<LocalDateTime> listaDisponibilidadBanco) {
        this.listaDisponibilidadBanco = listaDisponibilidadBanco;
    }

    public void annadirListaDisponibilidad(LocalDateTime horario){

        listaDisponibilidadBanco.add(horario);
    }

    public void annadirListaRecursosBancoDeTrabajo(RecursosBancoDeTrabajo recurso) {
        listaRecursosBanco.add(recurso);
    }

    @Override
    public String toString() {
        return "Banco de trabajo{" +
                "id del banco de trabajo ='" + id +
                ", descripción del banco de trabajo ='" + descripcionBanco +
                ", lista de recursos del banco de trabajo='" + listaRecursosBanco +
                ", lista de disponibilidad del banco de trabajo='" + listaDisponibilidadBanco +
                '}';
    }
}

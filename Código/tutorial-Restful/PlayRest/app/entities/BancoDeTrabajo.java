package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BancoDeTrabajo extends RecursoWeb{

    private String descripcionBanco;
    private int labID;
    private ArrayList<LocalDateTime> listaDisponibilidadBanco= new ArrayList<>();
    private ArrayList<RecursosBancoDeTrabajoShort> listaRecursosBanco= new ArrayList<>();


    public BancoDeTrabajo(){
        super();
    }

    public BancoDeTrabajo(int id, String url, String descripcionBanco, int labID) {
        super(id, url);
        this.descripcionBanco=descripcionBanco;
        this.labID=labID;
    }


    public String getDescripcionBanco() {
        return descripcionBanco;
    }

    public void setDescripcionBanco(String descripcionBanco) {
        this.descripcionBanco = descripcionBanco;
    }


    public int getLabID() {
        return labID;
    }

    public void setLabID(int labID) {
        this.labID = labID;
    }
    public ArrayList<RecursosBancoDeTrabajoShort> getListaRecursosBanco() {
        return listaRecursosBanco;
    }

    public void setListaRecursosBanco(ArrayList<RecursosBancoDeTrabajoShort> listaRecursosBanco) {
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

    public void annadirListaRecursosBancoDeTrabajo(RecursosBancoDeTrabajoShort recurso) {

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

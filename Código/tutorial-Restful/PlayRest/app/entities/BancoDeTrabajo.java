package entities;

import java.util.ArrayList;

public class BancoDeTrabajo extends RecursoWeb{

    private int idBanco;
    private String descripcionBanco;
    private ArrayList<String> listaDisponibilidadBanco;
    private ArrayList<RecursosBancoDeTrabajo> listaRecursosBanco;

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public String getDesccripcionBanco() {
        return descripcionBanco;
    }

    public void setDesccripcionBanco(String desccripcionBanco) {
        this.descripcionBanco = desccripcionBanco;
    }

    public ArrayList<RecursosBancoDeTrabajo> getListaRecursosBanco() {
        return listaRecursosBanco;
    }

    public void setListaRecursosBanco(ArrayList<RecursosBancoDeTrabajo> listaRecursosBanco) {
        this.listaRecursosBanco = listaRecursosBanco;
    }

    public ArrayList<String> getListaDisponibilidadBanco() {
        return listaDisponibilidadBanco;
    }

    public void setListaDisponibilidadBanco(ArrayList<String> listaDisponibilidadBanco) {
        this.listaDisponibilidadBanco = listaDisponibilidadBanco;
    }

    @Override
    public String toString() {
        return "Banco de trabajo{" +
                "id del banco de trabajo ='" + idBanco +
                ", descripción del banco de trabajo ='" + descripcionBanco +
                ", lista de recursos del banco de trabajo='" + listaRecursosBanco +
                ", lista de disponibilidad del banco de trabajo='" + listaDisponibilidadBanco +
                '}';
    }
}

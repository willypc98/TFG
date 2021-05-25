package entities;

import java.util.ArrayList;
import java.util.Date;

public class BancoDeTrabajo extends RecursoWeb{

    private String descripcionBanco;
    private ArrayList<Date> listaDisponibilidadBanco;
    private ArrayList<RecursosBancoDeTrabajo> listaRecursosBanco;

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

    public ArrayList<Date> getListaDisponibilidadBanco() {
        return listaDisponibilidadBanco;
    }

    public void setListaDisponibilidadBanco(ArrayList<Date> listaDisponibilidadBanco) {
        this.listaDisponibilidadBanco = listaDisponibilidadBanco;
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

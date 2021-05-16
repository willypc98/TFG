package entities;

import java.util.ArrayList;

public class BancoDeTrabajo {

    private int idBanco;
    private String descripcionBanco;
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

    @Override
    public String toString() {
        return "Banco de trabajo{" +
                "id del banco de trabajo ='" + idBanco +
                ", descripción del banco de trabajo ='" + descripcionBanco +
                ", lista de recursos del banco de trabajo='" + listaRecursosBanco +
                '}';
    }
}

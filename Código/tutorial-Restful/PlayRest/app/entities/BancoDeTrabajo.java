package entities;

import java.util.ArrayList;

public class BancoDeTrabajo {

    private int idBanco;
    private String desccripcionBanco;
    private ArrayList<RecursosBancoDeTrabajo> listaRecursosBanco;

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public String getDesccripcionBanco() {
        return desccripcionBanco;
    }

    public void setDesccripcionBanco(String desccripcionBanco) {
        this.desccripcionBanco = desccripcionBanco;
    }

    public ArrayList<RecursosBancoDeTrabajo> getListaRecursosBanco() {
        return listaRecursosBanco;
    }

    public void setListaRecursosBanco(ArrayList<RecursosBancoDeTrabajo> listaRecursosBanco) {
        this.listaRecursosBanco = listaRecursosBanco;
    }
}

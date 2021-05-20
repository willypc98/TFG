package entities;

import java.util.ArrayList;

public class Laboratorio extends RecursoWeb{

    private int idLab;
    private String nombreLab;
    private String descripcionLab;
    private ArrayList<String> listaDisponibilidadLaboratorio; //horas de apertura y cierre
    private ArrayList<BancoDeTrabajo> listaBancosDeTrabajo;

    public int getIdLab() {
        return idLab;
    }

    public void setIdLab(int idLab) {
        this.idLab = idLab;
    }

    public String getNombreLab() {
        return nombreLab;
    }

    public void setNombreLab(String nombreLab) {
        this.nombreLab = nombreLab;
    }

    public String getDesccripcionLab() {
        return descripcionLab;
    }

    public void setDesccripcionLab(String desccripcionLab) {
        this.descripcionLab = desccripcionLab;
    }


    public ArrayList<String> getListaDisponibilidadLaboratorio() {
        return listaDisponibilidadLaboratorio;
    }

    public void setListaDisponibilidadLaboratorio(ArrayList<String> listaDisponibilidadLaboratorio) {
        this.listaDisponibilidadLaboratorio = listaDisponibilidadLaboratorio;
    }

    public ArrayList<BancoDeTrabajo> getListaBancosDeTrabajo() {
        return listaBancosDeTrabajo;
    }

    public void setListaBancosDeTrabajo(ArrayList<BancoDeTrabajo> listaBancosDeTrabajo) {
        this.listaBancosDeTrabajo = listaBancosDeTrabajo;
    }

    @Override
    public String toString() {
        return "Laboratorio{" +
                "id del laboratorio= 'L." + idLab +
                ", nombre del laboratorio='" + nombreLab + '\'' +
                ", descripción del laboratorio='" + descripcionLab +
                ", horarios disponibles='" + listaDisponibilidadLaboratorio +
                '}';
    }
}

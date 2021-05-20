package entities;

import java.util.ArrayList;

public class Laboratorio {

    private int idLab;
    private String nombreLab;
    private String descripcionLab;
    private ArrayList<String> listaDisponibilidad; //horas de apertura y cierre
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

    public ArrayList<String> getListaDisponibilidad() {
        return listaDisponibilidad;
    }

    public void setListaDisponibilidad(ArrayList<String> listaDisponibilidad) {
        this.listaDisponibilidad = listaDisponibilidad;
    }

    @Override
    public String toString() {
        return "Laboratorio{" +
                "id del laboratorio= 'L." + idLab +
                ", nombre del laboratorio='" + nombreLab + '\'' +
                ", descripción del laboratorio='" + descripcionLab +
                ", horarios disponibles='" + listaDisponibilidad +
                '}';
    }
}

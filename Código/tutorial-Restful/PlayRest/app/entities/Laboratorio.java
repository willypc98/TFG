package entities;

import java.util.ArrayList;

public class Laboratorio {

    private int idLab;
    private String nombreLab;
    private String desccripcionLab;
    private ArrayList<String> listaDisponibilidad;

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
        return desccripcionLab;
    }

    public void setDesccripcionLab(String desccripcionLab) {
        this.desccripcionLab = desccripcionLab;
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
                ", descripción del laboratorio='" + desccripcionLab +
                ", horarios disponibles='" + listaDisponibilidad +
                '}';
    }
}

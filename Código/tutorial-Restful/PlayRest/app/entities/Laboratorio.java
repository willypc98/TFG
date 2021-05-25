package entities;

import java.util.ArrayList;
import java.util.Date;

public class Laboratorio extends RecursoWeb{

    private String nombreLab;
    private String descripcionLab;
    private ArrayList<Date> listaDisponibilidadLaboratorio; //horas de apertura y cierre
    private ArrayList<BancoDeTrabajo> listaBancosDeTrabajo;

    public Laboratorio(){
        super();
    }
    public Laboratorio(int id, String url, String nombreLab, String descripcionLab) {
        super(id, url);
        this.nombreLab=nombreLab;
        this.descripcionLab=descripcionLab;

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


    public ArrayList<Date> getListaDisponibilidadLaboratorio() {
        return listaDisponibilidadLaboratorio;
    }

    public void setListaDisponibilidadLaboratorio(ArrayList<Date> listaDisponibilidadLaboratorio) {
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
                "id del laboratorio= 'L." + id +
                ", nombre del laboratorio='" + nombreLab + '\'' +
                ", descripción del laboratorio='" + descripcionLab +
                ", horarios disponibles='" + listaDisponibilidadLaboratorio +
                '}';
    }
}

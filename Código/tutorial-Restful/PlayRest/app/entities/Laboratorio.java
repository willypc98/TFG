package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Laboratorio extends RecursoWeb{

    private String nombreLab;
    private String descripcionLab;
    private ArrayList<LocalDateTime> listaDisponibilidadLaboratorio; //horas de apertura y cierre 01/01/2021-10:00
    private ArrayList<BancoDeTrabajo> listaBancosDeTrabajo;

    public Laboratorio(){
        super();
    }
    /*
    public Laboratorio(){

        super(1, "/laboratorio/1");
        this.nombreLab="nombreLab";
        this.descripcionLab="descripcionLab";
        listaDisponibilidadLaboratorio = new ArrayList<>();
        listaDisponibilidadLaboratorio.add(LocalDateTime.of(2021,05,01,9,30));
        listaDisponibilidadLaboratorio.add(LocalDateTime.of(2021-1900,05,01,10,00 ));
    }
    */
     
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

    public String getDescripcionLab() {
        return descripcionLab;
    }

    public void setDescripcionLab(String desccripcionLab) {
        this.descripcionLab = desccripcionLab;
    }


    public ArrayList<LocalDateTime> getListaDisponibilidadLaboratorio() {
        return listaDisponibilidadLaboratorio;
    }

    public void setListaDisponibilidadLaboratorio(ArrayList<LocalDateTime> listaDisponibilidadLaboratorio) {
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

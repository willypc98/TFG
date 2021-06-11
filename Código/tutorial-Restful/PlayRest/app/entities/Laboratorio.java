package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Laboratorio extends RecursoWeb{

    private String nombreLab;
    private String descripcionLab;
    private ArrayList<LocalDateTime> listaDisponibilidadLaboratorio = new ArrayList<>(); //horas de apertura y cierre 01/01/2021-10:00
   // private ArrayList<BancoDeTrabajo> listaBancosDeTrabajo = new ArrayList<>();
 //  private ArrayList<String> listaBancosDeTrabajo = new ArrayList<>();
    private ArrayList<BancoDeTrabajoShort> listaBancosDeTrabajo = new ArrayList<>();


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
        listaDisponibilidadLaboratorio.add(LocalDateTime.of(2021,05,01,10,00 ));
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

    public void setDescripcionLab(String descripcionLab) {
        this.descripcionLab = descripcionLab;
    }


    public ArrayList<LocalDateTime> getListaDisponibilidadLaboratorio() {
        return listaDisponibilidadLaboratorio;
    }

    public void setListaDisponibilidadLaboratorio(ArrayList<LocalDateTime> listaDisponibilidadLaboratorio) {
        this.listaDisponibilidadLaboratorio = listaDisponibilidadLaboratorio;
    }

    public ArrayList<BancoDeTrabajoShort> getListaBancosDeTrabajo() {
        return listaBancosDeTrabajo;
    }

    public void setListaBancosDeTrabajo(ArrayList<BancoDeTrabajoShort> listaBancosDeTrabajo) {
        this.listaBancosDeTrabajo = listaBancosDeTrabajo;
    }

    public void annadirListaDisponibilidad(LocalDateTime horario){

        listaDisponibilidadLaboratorio.add(horario);
    }

    public void annadirListaBancosDeTrabajo(BancoDeTrabajoShort banco){
        listaBancosDeTrabajo.add(banco);
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

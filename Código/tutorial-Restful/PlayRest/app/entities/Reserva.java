package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reserva extends RecursoWeb{

    private Usuario usu;
    private Laboratorio lab;
    private BancoDeTrabajo ban;
    private ArrayList<RecursosBancoDeTrabajo> listaRecursos;
    private ArrayList<LocalDateTime> listaDisponibilidadReserva;

    public Reserva(){
        super();
    }
    public Reserva(int id, String url, Usuario usu, Laboratorio lab, BancoDeTrabajo ban ) {
        super(id, url);
        this.usu=usu;
        this.lab=lab;
        this.ban=ban;

    }

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public Laboratorio getLab() {
        return lab;
    }

    public void setLab(Laboratorio lab) {
        this.lab = lab;
    }

    public BancoDeTrabajo getBan() {
        return ban;
    }

    public void setBan(BancoDeTrabajo ban) {
        this.ban = ban;
    }

    public ArrayList<RecursosBancoDeTrabajo> getListaRecursos() {
        return listaRecursos;
    }

    public void setListaRecursos(ArrayList<RecursosBancoDeTrabajo> listaRecursos) {
        this.listaRecursos = listaRecursos;
    }

    public ArrayList<LocalDateTime> getListaDisponibilidadReserva() {
        return listaDisponibilidadReserva;
    }

    public void setListaDisponibilidadReserva(ArrayList<LocalDateTime> listaDisponibilidadReserva) {
        this.listaDisponibilidadReserva = listaDisponibilidadReserva;
    }
}

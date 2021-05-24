package entities;

import java.util.ArrayList;
import java.util.Date;

public class Reserva extends RecursoWeb{

    private Usuario usu;
    private Laboratorio lab;
    private BancoDeTrabajo ban;
    private ArrayList<RecursosBancoDeTrabajo> listaRecursos;
    private ArrayList<Date> listaDisponibilidadReserva;

    public Reserva(int id, String url, Usuario usu, Laboratorio lab, BancoDeTrabajo ban ,
                   ArrayList<RecursosBancoDeTrabajo> listaRecursos, ArrayList<Date> listaDisponibilidadReserva) {
        super(id, url);
        this.usu=usu;
        this.lab=lab;
        this.ban=ban;
        this.listaRecursos=listaRecursos;
        this.listaDisponibilidadReserva=listaDisponibilidadReserva;
    }
}

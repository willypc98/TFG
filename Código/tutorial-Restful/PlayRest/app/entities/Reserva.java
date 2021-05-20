package entities;

import java.util.ArrayList;
import java.util.Date;

public class Reserva extends RecursoWeb{

    private Usuario usu;
    private Laboratorio lab;
    private BancoDeTrabajo ban;
    private ArrayList<RecursosBancoDeTrabajo> listaRecursos;
    private ArrayList<Date> listaDisponibilidadReserva;

}

package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reserva extends RecursoWeb{

    private Usuario usu;
    private LaboratorioShort lab;
    private BancoDeTrabajoShort ban;
    private LocalDateTime DisponibilidadReserva;
    private ArrayList<RecursosBancoDeTrabajoShort> listaRecursos;

    public Reserva(){
        super();
    }
    public Reserva(int id, String url, Usuario usu, LaboratorioShort lab, BancoDeTrabajoShort ban ) {
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

    public LaboratorioShort getLab() {
        return lab;
    }

    public void setLab(LaboratorioShort lab) {
        this.lab = lab;
    }

    public BancoDeTrabajoShort getBan() {
        return ban;
    }

    public void setBan(BancoDeTrabajoShort ban) {
        this.ban = ban;
    }

    public LocalDateTime getDisponibilidadReserva() {
        return DisponibilidadReserva;
    }

    public void setDisponibilidadReserva(LocalDateTime disponibilidadReserva) {
        DisponibilidadReserva = disponibilidadReserva;
    }

    public ArrayList<RecursosBancoDeTrabajoShort> getListaRecursos() {
        return listaRecursos;
    }

    public void setListaRecursos(ArrayList<RecursosBancoDeTrabajoShort> listaRecursos) {
        this.listaRecursos = listaRecursos;
    }



    public void annadirListaRecursos(RecursosBancoDeTrabajoShort recurso){
        listaRecursos.add(recurso);
    }


}

package entities;

import java.time.LocalDateTime;

public class ReservaShort extends RecursoWeb {
    //private Usuario usu;
    private LocalDateTime DisponibilidadReserva;

    public ReservaShort() { super(); }

    public ReservaShort(int id, String url,LocalDateTime DisponibilidadReserva) {
        super(id, url);
        this.DisponibilidadReserva=DisponibilidadReserva;
    }
/*
    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }
*/
    public LocalDateTime getDisponibilidadReserva() {
        return DisponibilidadReserva;
    }

    public void setDisponibilidadReserva(LocalDateTime disponibilidadReserva) {
        DisponibilidadReserva = disponibilidadReserva;
    }


}

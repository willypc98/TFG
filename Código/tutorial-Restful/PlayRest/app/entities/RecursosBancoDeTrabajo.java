package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class RecursosBancoDeTrabajo extends RecursoWeb{

    private String nombreRecursoBanco;
    private String descripcionRecursoBanco;
    private int bancoID;
    private ArrayList<LocalDateTime> listaDisponibilidadRecursos= new ArrayList<>();

    public RecursosBancoDeTrabajo(){
        super();
    }

    public RecursosBancoDeTrabajo(int id, String url, String nombreRecursoBanco, String descripcionRecursoBanco, int bancoID ) {
        super(id, url);
        this.nombreRecursoBanco=nombreRecursoBanco;
        this.descripcionRecursoBanco=descripcionRecursoBanco;
        this.bancoID=bancoID;
    }

    public String getNombreRecursoBanco() {
        return nombreRecursoBanco;
    }

    public void setNombreRecursoBanco(String nombreRecursoBanco) {
        this.nombreRecursoBanco = nombreRecursoBanco;
    }

    public String getDescripcionRecursoBanco() {
        return descripcionRecursoBanco;
    }

    public void setDescripcionRecursoBanco(String descripcionRecursoBanco) {
        this.descripcionRecursoBanco = descripcionRecursoBanco;
    }

    public int getBancoID() { return bancoID; }

    public void setBancoID(int bancoID) { this.bancoID = bancoID; }

    public ArrayList<LocalDateTime> getListaDisponibilidadRecursos() {
        return listaDisponibilidadRecursos;
    }

    public void setListaDisponibilidadRecursos(ArrayList<LocalDateTime> listaDisponibilidadRecursos) {
        this.listaDisponibilidadRecursos = listaDisponibilidadRecursos;
    }

    public void annadirListaDisponibilidad(LocalDateTime horario){

        listaDisponibilidadRecursos.add(horario);
    }

    @Override
    public String toString() {
        return "Recurso del banco de trabajo {" +
                "id del recurso del banco de trabajo='" + id +
                ", nombre del recurso del banco de trabajo='" + nombreRecursoBanco + '\'' +
                ", descripción del recurso del banco de trabajo='" + descripcionRecursoBanco +
                ", lista de disponibilidad de los recursos del banco de trabajo='" + listaDisponibilidadRecursos +
                '}';
    }


}

package entities;

import java.util.ArrayList;
import java.util.Date;

public class RecursosBancoDeTrabajo extends RecursoWeb{

   // private int idRecursoBanco;
    private String nombreRecursoBanco;
    private String descripcionRecursoBanco;
    private ArrayList<Date> listaDisponibilidadRecursos;

    public RecursosBancoDeTrabajo(){
        super();
    }
    
    public RecursosBancoDeTrabajo(int id, String url, String nombreRecursoBanco, String descripcionRecursoBanco ) {
        super(id, url);
        this.nombreRecursoBanco=nombreRecursoBanco;
        this.descripcionRecursoBanco=descripcionRecursoBanco;
    }

    public String getNombreRecursoBanco() {
        return nombreRecursoBanco;
    }

    public void setNombreRecursoBanco(String nombreRecursoBanco) {
        this.nombreRecursoBanco = nombreRecursoBanco;
    }

    public String getDesccripcionRecursoBanco() {
        return descripcionRecursoBanco;
    }

    public void setDesccripcionRecursoBanco(String desccripcionRecursoBanco) {
        this.descripcionRecursoBanco = desccripcionRecursoBanco;
    }

    public ArrayList<Date> getListaDisponibilidadRecursos() {
        return listaDisponibilidadRecursos;
    }

    public void setListaDisponibilidadRecursos(ArrayList<Date> listaDisponibilidadRecursos) {
        this.listaDisponibilidadRecursos = listaDisponibilidadRecursos;
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

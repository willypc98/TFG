package entities;

import java.util.ArrayList;

public class RecursosBancoDeTrabajo {

    private int idRecursoBanco;
    private String nombreRecursoBanco;
    private String descripcionRecursoBanco;
    private ArrayList<String> listaDisponibilidad;

    public int getIdRecursoBanco() {
        return idRecursoBanco;
    }

    public void setIdRecursoBanco(int idRecursoBanco) {
        this.idRecursoBanco = idRecursoBanco;
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

    @Override
    public String toString() {
        return "Recurso del banco de trabajo {" +
                "id del recurso del banco de trabajo='" + idRecursoBanco +
                ", nombre del recurso del banco de trabajo='" + nombreRecursoBanco + '\'' +
                ", descripción del recurso del banco de trabajo='" + descripcionRecursoBanco +
                '}';
    }
}

package entities;

import java.util.ArrayList;

public class Usuario extends RecursoWeb{

    private String nombre;
    private String grado;
    private ArrayList<ReservaShort> listaReservas = new ArrayList<>();

   public Usuario(){
        super();
    }
   public Usuario(int id,String url,String nombre, String grado){
        super(id,url);
        this.nombre=nombre;
        this.grado=grado;

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public void annadirReservas(ReservaShort reserva){
        getListaReservas().add(reserva);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id del usuario=" + id +
                ", nombre='" + nombre + '\'' +
                ", grado universitario='" + grado +
                '}';
    }


    public ArrayList<ReservaShort> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(ArrayList<ReservaShort> listaReservas) {
        this.listaReservas = listaReservas;
    }
}


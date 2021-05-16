package entities;

public class Usuario {

    private int id;
    private String nombre;
    private String grado;


    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", nmbre='" + nombre + '\'' +
                ", grado universitario='" + grado +
                '}';
    }


}


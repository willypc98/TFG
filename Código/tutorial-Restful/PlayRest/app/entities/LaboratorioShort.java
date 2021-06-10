package entities;

public class LaboratorioShort extends RecursoWeb{

    private String nombreLab;
    private String descripcionLab;

    public LaboratorioShort(){
        super();

    }
    public LaboratorioShort(int id, String url, String nombreLab, String descripcionLab) {
        super(id, url);
        this.nombreLab=nombreLab;
        this.descripcionLab=descripcionLab;

    }

    public String getNombreLab() {
        return nombreLab;
    }

    public void setNombreLab(String nombreLab) {
        this.nombreLab = nombreLab;
    }

    public String getDescripcionLab() {
        return descripcionLab;
    }

    public void setDescripcionLab(String descripcionLab) {
        this.descripcionLab = descripcionLab;
    }

}

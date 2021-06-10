package entities;

public class BancoDeTrabajoShort extends RecursoWeb{
    private String descripcionBanco;
    private int labID;

    public BancoDeTrabajoShort(){
        super();
    }

    public BancoDeTrabajoShort(int id, String url, String descripcionBanco, int labID) {
        super(id, url);
        this.descripcionBanco=descripcionBanco;
        this.labID=labID;
    }

    public String getDescripcionBanco() {
        return descripcionBanco;
    }

    public void setDescripcionBanco(String descripcionBanco) {
        this.descripcionBanco = descripcionBanco;
    }


    public int getLabID() {
        return labID;
    }

    public void setLabID(int labID) {
        this.labID = labID;
    }
}

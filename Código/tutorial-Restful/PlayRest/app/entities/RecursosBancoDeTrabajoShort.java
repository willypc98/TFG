package entities;

public class RecursosBancoDeTrabajoShort extends RecursoWeb{

    private String nombreRecursoBanco;
    private String descripcionRecursoBanco;
    private int labID;
    private int bancoID;

    public RecursosBancoDeTrabajoShort(){
        super();
    }

    public RecursosBancoDeTrabajoShort(int id, String url, String nombreRecursoBanco, String descripcionRecursoBanco, int bancoID, int labID ) {
        super(id, url);
        this.nombreRecursoBanco=nombreRecursoBanco;
        this.descripcionRecursoBanco=descripcionRecursoBanco;
        this.labID=labID;
        this.bancoID=bancoID;

    }

    public int getLabID() { return labID; }

    public void setLabID(int labID) { this.labID = labID; }

    public int getBancoID() { return bancoID; }

    public void setBancoID(int bancoID) { this.bancoID = bancoID; }


}

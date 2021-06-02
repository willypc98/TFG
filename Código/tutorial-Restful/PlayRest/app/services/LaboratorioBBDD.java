package services;


import entities.Laboratorio;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LaboratorioBBDD extends ConexionBBDD{

    private static LaboratorioBBDD instance;
    public static LaboratorioBBDD getInstance() {
        if (instance == null) {
            instance = new LaboratorioBBDD();
        }
        return instance;
    }


    public Laboratorio addLaboratorio(Laboratorio lab) throws SQLException, ClassNotFoundException {
        if (conector() == true) {

            try {

                con.setAutoCommit(false);
                int id = lab.getId();
                String url = lab.getUrl();
                String nombre = lab.getNombreLab();
                String descripcion = lab.getDescripcionLab();
                ArrayList<LocalDateTime> disponibilidad= lab.getListaDisponibilidadLaboratorio();



                createStatement.executeUpdate("INSERT INTO laboratorio (id,url,nombre,descripcion) VALUES (" + id + ", '" + url + "', '" + nombre + "', '" + descripcion + "')");

                for (LocalDateTime dis:disponibilidad) {
                    createStatement.executeUpdate("INSERT INTO DisponibilidadLaboratorio (labid,disponibilidad) VALUES (" + id + ", '" + dis +  "')");
                }
                con.commit();
                con.setAutoCommit(true);
                con.close();
            }
            catch(SQLException e){
                con.rollback();
                }

        }
        return lab;
    }

    public Laboratorio getLaboratorio(int id) {
        Laboratorio lab = new Laboratorio();
        try {
            if(conector()==true){

                String queryBBDD = "select * from laboratorio where id=" + id + ";";
                int i=0;
                try {
                    rS = createStatement.executeQuery(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (rS == null){
                    lab= null;

                }
                else{

                    try {
                        while (rS.next()) {
                            lab.setId(rS.getInt("id"));
                            lab.setUrl(rS.getString("url"));
                            lab.setNombreLab(rS.getString("nombre"));
                            lab.setDescripcionLab(rS.getString("descripcion"));
                          // lab.setListaDisponibilidadLaboratorio(rS.getLocalDateTime("disponibilidad"));
                           // lab.setListaDisponibilidadLaboratorio(rS.getDate("disponibilidad"));


                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        i = 0;
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
            else{
                lab=null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lab;
    }

    public ArrayList<Laboratorio> getAllLaboratorios() {
        ArrayList<Laboratorio> laboratoriosLista = new ArrayList();
        try {
            if(conector()==true){
                String queryBBDD = "select * from laboratorio;";
                int i=0;
                try {
                    rS = createStatement.executeQuery(queryBBDD);

                    while (rS.next()) {
                        Laboratorio lab = new Laboratorio();
                        lab.setId(Integer.parseInt(rS.getString("id")));
                        lab.setUrl(rS.getString("url"));
                        lab.setNombreLab(rS.getString("nombre"));
                        lab.setDescripcionLab(rS.getString("descripcion"));
                        laboratoriosLista.add(lab);

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    i=0;
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            else{
                return laboratoriosLista;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("El tamaño de la lista es" + laboratoriosLista.size());
        return laboratoriosLista;

    }
    /*
    public Laboratorio getDisponibilidadLaboratorio(int id) {
        Laboratorio lab = new Laboratorio();
        try {
            if(conector()==true){

                String queryBBDD = "select * from disponibilidadlaboratorio where id=" + id + ";";
                int i=0;
                try {
                    rS = createStatement.executeQuery(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (rS == null){
                    lab= null;

                }
                else{

                    try {
                        while (rS.next()) {
                            lab.setId(rS.getInt("id"));
                            lab.setUrl(rS.getString("url"));
                            lab.setNombreLab(rS.getString("nombre"));
                            lab.setDescripcionLab(rS.getString("descripcion"));


                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        i = 0;
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
            else{
                lab=null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lab;
    }

     */
    public Laboratorio updateLaboratorio(Laboratorio lab, int id) throws SQLException, ClassNotFoundException {
        try {
            if (conector() == true) {
               // int id = lab.getId();
                //String url = lab.getUrl();
                String nombre = lab.getNombreLab();
                String descripcion= lab.getDescripcionLab();

                String queryBBDD = "update Laboratorio set nombre='"+nombre+"', descripcion='"+descripcion+"' where id="+id+";";

                try {
                    createStatement.executeUpdate(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {

                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{

            }
        } catch (SQLException ex) {
            Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lab;
    }


    public boolean deleteLaboratorio(int id) throws SQLException, ClassNotFoundException {
        boolean valor= false;
        try {
            if (conector() == true) {

                String queryBBDD = "delete from laboratorio where id="+id+";";

                try {
                    createStatement.executeUpdate(queryBBDD);
                    valor = true;
                    return valor;
                } catch (SQLException ex) {
                    Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {

                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{

            }
        } catch (SQLException ex) {
            Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }


}

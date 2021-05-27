package services;

import entities.BancoDeTrabajo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class BancoDeTrabajoBBDD extends ConexionBBDD{

    private static BancoDeTrabajoBBDD instance;
    public static BancoDeTrabajoBBDD getInstance() {
        if (instance == null) {
            instance = new BancoDeTrabajoBBDD();
        }
        return instance;
    }

    /*
    public BancoDeTrabajo addBancoDeTrabajo(BancoDeTrabajo lab) throws SQLException, ClassNotFoundException {
        if (conector() == true) {

            int id = lab.getId();
            String url = lab.getUrl();



            createStatement.executeUpdate("INSERT INTO bancoDeTrabajo (id,url,nombre,grado) VALUES ("+id+", '" + url + "', '" + nombre + "', '" + grado + "')");
            con.close();

        }
        return lab;
    }

    public BancoDeTrabajo getBancoDeTrabajo(int id) {
        BancoDeTrabajo lab = new BancoDeTrabajo();
        try {
            if(conector()==true){

                String queryBBDD = "select * from bancoDeTrabajo where id=" + id + ";";
                int i=0;
                try {
                    rS = createStatement.executeQuery(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(BancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (rS == null){
                    lab= null;

                }
                else{

                    try {
                        while (rS.next()) {
                            lab.setId(rS.getInt("id"));
                            lab.setUrl(rS.getString("url"));
                            lab.setNombre(rS.getString("nombre"));
                            lab.setGrado(rS.getString("grado"));


                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(BancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        i = 0;
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(BancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<BancoDeTrabajo> getAllBancoDeTrabajo() {
        ArrayList<BancoDeTrabajo> bancoDeTrabajoLista = new ArrayList();
        try {
            if(conector()==true){
                String queryBBDD = "select * from bancoDeTrabajo;";
                int i=0;
                try {
                    rS = createStatement.executeQuery(queryBBDD);

                    while (rS.next()) {
                        BancoDeTrabajo lab = new BancoDeTrabajo();
                        lab.setId(Integer.parseInt(rS.getString("id")));
                        lab.setUrl(rS.getString("url"));
                        lab.setNombre(rS.getString("nombre"));
                        lab.setGrado(rS.getString("grado"));
                        bancoDeTrabajoLista.add(lab);

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    i=0;
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            else{
                return bancoDeTrabajoLista;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("El tamaño de la lista es" + bancoDeTrabajoLista.size());
        return bancoDeTrabajoLista;

    }
    public BancoDeTrabajo updateBancoDeTrabajo(BancoDeTrabajo lab ) throws SQLException, ClassNotFoundException {
        try {
            if (conector() == true) {
                int id = lab.getId();
                //String url = lab.getUrl();
                String nombre = lab.getNombre();
                String grado= lab.getGrado();

                String queryBBDD = "update BancoDeTrabajo set nombre='"+nombre+"', grado='"+grado+"' where id="+id+";";

                try {
                    createStatement.executeUpdate(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(BancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {

                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{

            }
        } catch (SQLException ex) {
            Logger.getLogger(BancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lab;
    }


    public boolean deleteBancoDeTrabajo(int id) throws SQLException, ClassNotFoundException {
        boolean valor= false;
        try {
            if (conector() == true) {

                String queryBBDD = "delete from bancoDeTrabajo where id="+id+";";

                try {
                    createStatement.executeUpdate(queryBBDD);
                    valor = true;
                    return valor;
                } catch (SQLException ex) {
                    Logger.getLogger(BancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {

                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{

            }
        } catch (SQLException ex) {
            Logger.getLogger(BancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }

 */
}

package services;

import entities.RecursosBancoDeTrabajo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecursosBancoDeTrabajoBBDD extends ConexionBBDD{


    private static RecursosBancoDeTrabajoBBDD instance;
    public static RecursosBancoDeTrabajoBBDD getInstance() {
        if (instance == null) {
            instance = new RecursosBancoDeTrabajoBBDD();
        }
        return instance;
    }

    /*
    public RecursosBancoDeTrabajo addRecursosBancoDeTrabajo(RecursosBancoDeTrabajo recurso) throws SQLException, ClassNotFoundException {
        if (conector() == true) {

            int id = recurso.getId();
            String url = recurso.getUrl();



            createStatement.executeUpdate("INSERT INTO recursosBancoDeTrabajo (id,url,nombre,grado) VALUES ("+id+", '" + url + "', '" + nombre + "', '" + grado + "')");
            con.close();

        }
        return recurso;
    }

    public RecursosBancoDeTrabajo getRecursosBancoDeTrabajo(int id) {
        RecursosBancoDeTrabajo recurso = new RecursosBancoDeTrabajo();
        try {
            if(conector()==true){

                String queryBBDD = "select * from recursosBancoDeTrabajo where id=" + id + ";";
                int i=0;
                try {
                    rS = createStatement.executeQuery(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(RecursosBancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (rS == null){
                    recurso= null;

                }
                else{

                    try {
                        while (rS.next()) {
                            recurso.setId(rS.getInt("id"));
                            recurso.setUrl(rS.getString("url"));
                            recurso.setNombre(rS.getString("nombre"));
                            recurso.setGrado(rS.getString("grado"));


                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(RecursosBancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        i = 0;
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(RecursosBancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
            else{
                recurso=null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recurso;
    }

    public ArrayList<RecursosBancoDeTrabajo> getAllRecursosBancoDeTrabajo() {
        ArrayList<RecursosBancoDeTrabajo> recursosBancoDeTrabajoLista = new ArrayList();
        try {
            if(conector()==true){
                String queryBBDD = "select * from recursosBancoDeTrabajo;";
                int i=0;
                try {
                    rS = createStatement.executeQuery(queryBBDD);

                    while (rS.next()) {
                        RecursosBancoDeTrabajo recurso = new RecursosBancoDeTrabajo();
                        recurso.setId(Integer.parseInt(rS.getString("id")));
                        recurso.setUrl(rS.getString("url"));
                        recurso.setNombre(rS.getString("nombre"));
                        recurso.setGrado(rS.getString("grado"));
                        recursosBancoDeTrabajoLista.add(recurso);

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(RecursosBancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    i=0;
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RecursosBancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            else{
                return recursosBancoDeTrabajoLista;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("El tamaño de la lista es" + recursosBancoDeTrabajoLista.size());
        return recursosBancoDeTrabajoLista;

    }
    public RecursosBancoDeTrabajo updateRecursosBancoDeTrabajo(RecursosBancoDeTrabajo recurso ) throws SQLException, ClassNotFoundException {
        try {
            if (conector() == true) {
                int id = recurso.getId();
                //String url = recurso.getUrl();
                String nombre = recurso.getNombre();
                String grado= recurso.getGrado();

                String queryBBDD = "update RecursosBancoDeTrabajo set nombre='"+nombre+"', grado='"+grado+"' where id="+id+";";

                try {
                    createStatement.executeUpdate(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(RecursosBancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {

                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RecursosBancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{

            }
        } catch (SQLException ex) {
            Logger.getLogger(RecursosBancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecursosBancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recurso;
    }


    public boolean deleteRecursosBancoDeTrabajo(int id) throws SQLException, ClassNotFoundException {
        boolean valor= false;
        try {
            if (conector() == true) {

                String queryBBDD = "delete from recursosBancoDeTrabajo where id="+id+";";

                try {
                    createStatement.executeUpdate(queryBBDD);
                    valor = true;
                    return valor;
                } catch (SQLException ex) {
                    Logger.getLogger(RecursosBancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {

                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RecursosBancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{

            }
        } catch (SQLException ex) {
            Logger.getLogger(RecursosBancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecursosBancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }
*/
}

package services;

import entities.RecursosBancoDeTrabajo;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
     public BancoDeTrabajo addBancoDeTrabajo(BancoDeTrabajo banco, int labID) throws SQLException, ClassNotFoundException {
        int identificador= -1;
        if (conector() == true) {
            con.setAutoCommit(false);
            try {


                String descripcion = banco.getDescripcionBanco();
                ArrayList<LocalDateTime> disponibilidad = new ArrayList<>();
                disponibilidad= banco.getListaDisponibilidadBanco();

                System.out.println("El identifacdor del laboratorio es: " + labID);
                createStatement.executeUpdate("INSERT INTO bancoDeTrabajo (descripcion,labid) VALUES ('" + descripcion + "', " + labID+");",Statement.RETURN_GENERATED_KEYS);
                ResultSet prueba = createStatement.getGeneratedKeys();
                prueba.next();
                identificador=prueba.getInt(1);
                System.out.println("la fila es " + identificador );
                String patron = "/laboratorios/" + labID + "/bancos/";
                String url = patron+identificador;
                createStatement.executeUpdate("UPDATE  BancoDeTrabajo set url ='" + url + "' where id = "+ identificador + ";");

                for (LocalDateTime dis:disponibilidad) {

                    createStatement.executeUpdate("INSERT INTO DisponibilidadBancoDeTrabajo (bancoid,disponibilidad) VALUES (" + identificador + ", '" + dis +  "');");
                }
                con.commit();
                con.setAutoCommit(true);
                con.close();
            }
            catch(SQLException e){
                con.rollback();
            }

        }
       // return banco;
        return getBancoDeTrabajo(labID,identificador);
    }
     */


    public RecursosBancoDeTrabajo addRecursosBancoDeTrabajo(RecursosBancoDeTrabajo recurso, int labID, int bancoID) throws SQLException, ClassNotFoundException {
        int identificador= -1;
        if (conector() == true) {
            con.setAutoCommit(false);
            try {


                String nombre = recurso.getNombreRecursoBanco();
                String descripcion = recurso.getDescripcionRecursoBanco();
                ArrayList<LocalDateTime> disponibilidad = new ArrayList<>();
                disponibilidad= recurso.getListaDisponibilidadRecursos();

                createStatement.executeUpdate("INSERT INTO RecursosBancoDeTrabajo (nombre,descripcion,labID, bancoID) VALUES ('" + nombre + "', '" + descripcion + "', " + labID+ ", "  + bancoID+");",Statement.RETURN_GENERATED_KEYS);
                ResultSet prueba = createStatement.getGeneratedKeys();
                prueba.next();
                identificador=prueba.getInt(1);
                System.out.println("la fila es " + identificador );
                String patron = "/laboratorios/" + labID + "/bancos/" + bancoID + "/recursos/";
                String url = patron+identificador;
                createStatement.executeUpdate("UPDATE  RecursosBancoDeTrabajo set url ='" + url + "' where id = "+ identificador + ";");

                for (LocalDateTime dis:disponibilidad) {

                    createStatement.executeUpdate("INSERT INTO DisponibilidadRecursosBancoDeTrabajo (recursoid,disponibilidad) VALUES (" + identificador + ", '" + dis +  "');");
                }
                con.commit();
                con.setAutoCommit(true);
                con.close();
            }
            catch(SQLException e){
                con.rollback();
            }

        }
        //return recurso;
         return getRecursosBancoDeTrabajo(labID, bancoID , identificador);
    }

    public RecursosBancoDeTrabajo getRecursosBancoDeTrabajo(int labID, int bancoID , int id) {

        HashMap<Integer,RecursosBancoDeTrabajo> mapa = new HashMap<>();
        try {
            if(conector()==true){

                String queryBBDD = "select RecursosBancoDeTrabajo.id, RecursosBancoDeTrabajo.url, RecursosBancoDeTrabajo.nombre , RecursosBancoDeTrabajo.descripcion, RecursosBancoDeTrabajo.labID, RecursosBancoDeTrabajo.bancoID, disponibilidadRecursosBancoDeTrabajo.disponibilidad from RecursosBancoDeTrabajo inner join disponibilidadRecursosBancoDeTrabajo on RecursosBancoDeTrabajo.id = disponibilidadRecursosBancoDeTrabajo.recursoID where RecursosBancoDeTrabajo.id =" + id +" AND RecursosBancoDeTrabajo.labID= " + labID + " AND RecursosBancoDeTrabajo.bancoID= "+ bancoID+" ;";
                int i=0;

                try {

                    rS = createStatement.executeQuery(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(RecursosBancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (rS == null){
                    //banco= null;

                }
                else{

                    try {
                        while (rS.next()) {
                            RecursosBancoDeTrabajo recurso;

                            if (mapa.containsKey(Integer.parseInt(rS.getString("RecursosBancoDeTrabajo.id")))){
                                recurso=mapa.get(Integer.parseInt(rS.getString("RecursosBancoDeTrabajo.id")));

                            }
                            else{

                                recurso = new RecursosBancoDeTrabajo();
                                recurso.setId(Integer.parseInt(rS.getString("RecursosBancoDeTrabajo.id")));
                                recurso.setUrl(rS.getString("RecursosBancoDeTrabajo.url"));
                                recurso.setNombreRecursoBanco(rS.getString("RecursosBancoDeTrabajo.nombre"));
                                recurso.setDescripcionRecursoBanco(rS.getString("RecursosBancoDeTrabajo.descripcion"));
                                recurso.setLabID(Integer.parseInt(rS.getString("RecursosBancoDeTrabajo.labID")));
                                recurso.setBancoID(Integer.parseInt(rS.getString("RecursosBancoDeTrabajo.bancoID")));


                                mapa.put(recurso.getId(), recurso);
                            }


                            LocalDateTime tiempo = rS.getObject("disponibilidadRecursosBancoDeTrabajo.disponibilidad",LocalDateTime.class);

                            recurso.annadirListaDisponibilidad(tiempo);





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
                //banco=null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(RecursosBancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecursosBancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (mapa.values().size() >0){


            return new ArrayList<>(mapa.values()).get(0);

        }
        else {
            return null;
        }


    }

    public Collection<RecursosBancoDeTrabajo> getAllRecursosBancosDeTrabajos(int labID, int bancoID) {

        HashMap<Integer,RecursosBancoDeTrabajo> mapa = new HashMap<>();

        try {
            if(conector()==true){
                 String queryBBDD = "select RecursosBancoDeTrabajo.id, RecursosBancoDeTrabajo.url, RecursosBancoDeTrabajo.nombre , RecursosBancoDeTrabajo.descripcion, RecursosBancoDeTrabajo.bancoID, disponibilidadRecursosBancoDeTrabajo.disponibilidad from RecursosBancoDeTrabajo inner join disponibilidadRecursosBancoDeTrabajo on RecursosBancoDeTrabajo.id = disponibilidadRecursosBancoDeTrabajo.recursoID WHERE RecursosBancoDeTrabajo.bancoID= "+ bancoID+ ";";
                int i=0;
                try {
                    rS = createStatement.executeQuery(queryBBDD);

                    while (rS.next()) {

                        RecursosBancoDeTrabajo recurso;

                        if (mapa.containsKey(Integer.parseInt(rS.getString("RecursosBancoDeTrabajo.id")))){
                            recurso=mapa.get(Integer.parseInt(rS.getString("RecursosBancoDeTrabajo.id")));
                        }
                        else{
                            recurso = new RecursosBancoDeTrabajo();
                            recurso.setId(Integer.parseInt(rS.getString("RecursosBancoDeTrabajo.id")));
                            recurso.setUrl(rS.getString("RecursosBancoDeTrabajo.url"));
                            recurso.setNombreRecursoBanco(rS.getString("RecursosBancoDeTrabajo.nombre"));
                            recurso.setDescripcionRecursoBanco(rS.getString("RecursosBancoDeTrabajo.descripcion"));
                            recurso.setBancoID(Integer.parseInt(rS.getString("RecursosBancoDeTrabajo.bancoID")));
                            mapa.put(recurso.getId(), recurso);
                        }


                        LocalDateTime tiempo = rS.getObject("disponibilidadRecursosBancoDeTrabajo.disponibilidad",LocalDateTime.class);
                        recurso.annadirListaDisponibilidad(tiempo);


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
                //return new ArrayList<>(mapa.values);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecursosBancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecursosBancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("El tamaño de la lista es" + mapa.values().size());
        return mapa.values();

    }

    public RecursosBancoDeTrabajo updateRecursosBancoDeTrabajo(RecursosBancoDeTrabajo recurso,int labID, int bancoID, int id) throws SQLException, ClassNotFoundException {
        try {
            if (conector() == true) {
                // int id = banco.getId();
                //String url = banco.getUrl();

                String nombre = recurso.getNombreRecursoBanco();
                String descripcion= recurso.getDescripcionRecursoBanco();
                //int bancoID = recurso.getBancoID();

                String queryBBDD = "update RecursosBancoDeTrabajo set nombre='" + nombre + "', descripcion ='" + descripcion + "'  where id="+id+" AND bancoID= " + bancoID+ " ;";

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


    public boolean deleteRecursosBancoDeTrabajo(int labID, int bancoID, int id) throws SQLException, ClassNotFoundException {
        boolean valor= false;
        try {
            if (conector() == true) {

                String queryBBDD = "delete from RecursosBancoDeTrabajo where id="+id+" AND bancoID = "+ bancoID + ";";

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

}

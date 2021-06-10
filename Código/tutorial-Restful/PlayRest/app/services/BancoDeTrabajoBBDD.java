package services;

import entities.BancoDeTrabajo;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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

    public BancoDeTrabajo getBancoDeTrabajo(int labID, int id) {

        HashMap<Integer,BancoDeTrabajo> mapa = new HashMap<>();
        try {
            if(conector()==true){

                //String queryBBDD = "select * from bancoDeTrabajo where id=" + id + ";";
                //  String queryBBDD = "select bancoDeTrabajo.id, bancoDeTrabajo.url, bancoDeTrabajo.nombre, bancoDeTrabajo.descripcion, disponibilidadbancoDeTrabajo.disponibilidad from bancoDeTrabajo, disponibilidadbancoDeTrabajo where bancoDeTrabajo.id=" + id + " order by bancoDeTrabajo.id ASC , disponibilidadbancoDeTrabajo.disponibilidad ASC;";
                String queryBBDD = "select bancoDeTrabajo.id, bancoDeTrabajo.url, bancoDeTrabajo.descripcion, bancodetrabajo.labid, disponibilidadbancoDeTrabajo.disponibilidad , recursosbancodetrabajo.id as recursoID from bancoDeTrabajo inner join disponibilidadbancoDeTrabajo on bancoDeTrabajo.id = disponibilidadbancoDeTrabajo.bancoid LEFT JOIN recursosbancodetrabajo on bancodetrabajo.id = recursosbancodetrabajo.bancoid where bancoDeTrabajo.id =" + id + " AND bancoDetrabajo.labid =" + labID +" ;";
                int i=0;

                try {

                    rS = createStatement.executeQuery(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(BancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (rS == null){
                    //banco= null;

                }
                else{

                    try {
                        while (rS.next()) {
                            BancoDeTrabajo banco;
                          
                            if (mapa.containsKey(Integer.parseInt(rS.getString("bancoDeTrabajo.id")))){
                                banco=mapa.get(Integer.parseInt(rS.getString("bancoDeTrabajo.id")));
                                
                            }
                            else{
                               
                                banco = new BancoDeTrabajo();
                                banco.setId(Integer.parseInt(rS.getString("bancoDeTrabajo.id")));
                                banco.setUrl(rS.getString("bancoDeTrabajo.url"));
                                banco.setDescripcionBanco(rS.getString("bancoDeTrabajo.descripcion"));
                                banco.setLabID(Integer.parseInt(rS.getString("bancoDeTrabajo.labid")));
                                
                                mapa.put(banco.getId(), banco);
                            }

                           
                            LocalDateTime tiempo = rS.getObject("disponibilidadbancoDeTrabajo.disponibilidad",LocalDateTime.class);
                            
                            banco.annadirListaDisponibilidad(tiempo);

                            String recurso = rS.getString("recursoID");
                            banco.annadirListaRecursosBancoDeTrabajo(recurso);



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
                //banco=null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(BancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (mapa.values().size() >0){


            return new ArrayList<>(mapa.values()).get(0);

        }
        else {
            return null;
        }


    }

    public Collection<BancoDeTrabajo> getAllBancosDeTrabajos(int labID) {

        HashMap<Integer,BancoDeTrabajo> mapa = new HashMap<>();

        try {
            if(conector()==true){
                // String queryBBDD = "select * from bancoDeTrabajo;";
                // String queryBBDD = "select bancoDeTrabajo.id, bancoDeTrabajo.url, bancoDeTrabajo.nombre, bancoDeTrabajo.descripcion, disponibilidadbancoDeTrabajo.disponibilidad from bancoDeTrabajo, disponibilidadbancoDeTrabajo order by bancoDeTrabajo.id ASC , disponibilidadbancoDeTrabajo.disponibilidad ASC;";
                String queryBBDD = "select bancoDeTrabajo.id, bancoDeTrabajo.url, bancoDeTrabajo.descripcion, bancodetrabajo.labid, disponibilidadbancoDeTrabajo.disponibilidad , recursosbancodetrabajo.id as recursoID from bancoDeTrabajo inner join disponibilidadbancoDeTrabajo on bancoDeTrabajo.id = disponibilidadbancoDeTrabajo.bancoid LEFT JOIN recursosbancodetrabajo on bancodetrabajo.id = recursosbancodetrabajo.bancoid where bancoDeTrabajo.labid =" + labID + " ;";
                int i=0;
                try {
                    rS = createStatement.executeQuery(queryBBDD);

                    while (rS.next()) {

                        BancoDeTrabajo banco;

                        if (mapa.containsKey(Integer.parseInt(rS.getString("bancoDeTrabajo.id")))){
                            banco=mapa.get(Integer.parseInt(rS.getString("bancoDeTrabajo.id")));
                        }
                        else{
                            banco = new BancoDeTrabajo();
                            banco.setId(Integer.parseInt(rS.getString("bancoDeTrabajo.id")));
                            banco.setUrl(rS.getString("bancoDeTrabajo.url"));
                            banco.setDescripcionBanco(rS.getString("bancoDeTrabajo.descripcion"));
                            banco.setLabID(Integer.parseInt(rS.getString("bancodetrabajo.labid")));
                            mapa.put(banco.getId(), banco);
                        }


                        LocalDateTime tiempo = rS.getObject("disponibilidadbancoDeTrabajo.disponibilidad",LocalDateTime.class);
                        banco.annadirListaDisponibilidad(tiempo);

                        String recurso = rS.getString("recursoID");
                        banco.annadirListaRecursosBancoDeTrabajo(recurso);

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
                //return new ArrayList<>(mapa.values);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BancoDeTrabajoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("El tamaño de la lista es" + mapa.values().size());
        return mapa.values();

    }

    public BancoDeTrabajo updateBancoDeTrabajo(BancoDeTrabajo banco,int labID, int id) throws SQLException, ClassNotFoundException {
        try {
            if (conector() == true) {
                // int id = banco.getId();
                //String url = banco.getUrl();

                String descripcion= banco.getDescripcionBanco();

                String queryBBDD = "update BancoDeTrabajo set descripcion='"+descripcion+"' where id="+id+" AND labID= " + labID + ";";

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
        return banco;
    }


    public boolean deleteBancoDeTrabajo(int labID,int id) throws SQLException, ClassNotFoundException {
        boolean valor= false;
        try {
            if (conector() == true) {

                String queryBBDD = "delete from bancoDeTrabajo where id="+id+" AND labID= " + labID + ";";

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

}

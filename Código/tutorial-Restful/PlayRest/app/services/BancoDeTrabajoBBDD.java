package services;

import entities.BancoDeTrabajo;
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

    public BancoDeTrabajo addBancoDeTrabajo(BancoDeTrabajo banco) throws SQLException, ClassNotFoundException {
        if (conector() == true) {
            con.setAutoCommit(false);
            try {

                //con.setAutoCommit(false);
                int id = banco.getId();
                String url = banco.getUrl();
                String descripcion = banco.getDescripcionBanco();
                int labID = banco.getLabID();
                ArrayList<LocalDateTime> disponibilidad = new ArrayList<>();
                disponibilidad= banco.getListaDisponibilidadBanco();

                //createStatement.executeUpdate("INSERT INTO bancoDeTrabajo (id,url,descripcion) VALUES (" + id + ", '" + url + "', '" + descripcion + "');");
                createStatement.executeUpdate("INSERT INTO bancoDeTrabajo (id,url,descripcion,labid) VALUES (" + id + " , '" + url + "', '" + descripcion + "', " + labID+");");


                for (LocalDateTime dis:disponibilidad) {

                    createStatement.executeUpdate("INSERT INTO DisponibilidadBancoDeTrabajo (bancoid,disponibilidad) VALUES (" + id + ", '" + dis +  "');");
                }
                con.commit();
                con.setAutoCommit(true);
                con.close();
            }
            catch(SQLException e){
                con.rollback();
            }

        }
        return banco;
    }

    public BancoDeTrabajo getBancoDeTrabajo(int id) {

        HashMap<Integer,BancoDeTrabajo> mapa = new HashMap<>();
        try {
            if(conector()==true){

                //String queryBBDD = "select * from bancoDeTrabajo where id=" + id + ";";
                //  String queryBBDD = "select bancoDeTrabajo.id, bancoDeTrabajo.url, bancoDeTrabajo.nombre, bancoDeTrabajo.descripcion, disponibilidadbancoDeTrabajo.disponibilidad from bancoDeTrabajo, disponibilidadbancoDeTrabajo where bancoDeTrabajo.id=" + id + " order by bancoDeTrabajo.id ASC , disponibilidadbancoDeTrabajo.disponibilidad ASC;";
                String queryBBDD = "select bancoDeTrabajo.id, bancoDeTrabajo.url, bancoDeTrabajo.descripcion, bancodetrabajo.labid, disponibilidadbancoDeTrabajo.disponibilidad from bancoDeTrabajo inner join disponibilidadbancoDeTrabajo on bancoDeTrabajo.id = disponibilidadbancoDeTrabajo.bancoid where bancoDeTrabajo.id =" + id + " ;";
                
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
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (mapa.values().size() >0){


            return new ArrayList<>(mapa.values()).get(0);

        }
        else {
            return null;
        }


    }

    public Collection<BancoDeTrabajo> getAllBancosDeTrabajos() {

        HashMap<Integer,BancoDeTrabajo> mapa = new HashMap<>();

        try {
            if(conector()==true){
                // String queryBBDD = "select * from bancoDeTrabajo;";
                // String queryBBDD = "select bancoDeTrabajo.id, bancoDeTrabajo.url, bancoDeTrabajo.nombre, bancoDeTrabajo.descripcion, disponibilidadbancoDeTrabajo.disponibilidad from bancoDeTrabajo, disponibilidadbancoDeTrabajo order by bancoDeTrabajo.id ASC , disponibilidadbancoDeTrabajo.disponibilidad ASC;";
                String queryBBDD = "select bancoDeTrabajo.id, bancoDeTrabajo.url, bancoDeTrabajo.descripcion, bancodetrabajo.labid, disponibilidadbancoDeTrabajo.disponibilidad from bancoDeTrabajo inner join disponibilidadbancoDeTrabajo on bancoDeTrabajo.id = disponibilidadbancoDeTrabajo.bancoid;";
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
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("El tamaño de la lista es" + mapa.values().size());
        return mapa.values();

    }

    public BancoDeTrabajo updateBancoDeTrabajo(BancoDeTrabajo banco, int id) throws SQLException, ClassNotFoundException {
        try {
            if (conector() == true) {
                // int id = banco.getId();
                //String url = banco.getUrl();

                String descripcion= banco.getDescripcionBanco();

                String queryBBDD = "update BancoDeTrabajo set descripcion='"+descripcion+"' where id="+id+";";

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

}

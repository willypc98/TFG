package services;

import entities.ModifHoraria;
import entities.RecursosBancoDeTrabajo;
import entities.RecursosBancoDeTrabajoShort;

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




    public RecursosBancoDeTrabajo addRecursosBancoDeTrabajo(RecursosBancoDeTrabajo recurso, int labID, int bancoID) throws SQLException, ClassNotFoundException {
        int identificador= -1;
        if (conector() == true) {
            con.setAutoCommit(false);
            try {


                String nombre = recurso.getNombreRecursoBanco();
                String descripcion = recurso.getDescripcionRecursoBanco();
                ArrayList<LocalDateTime> disponibilidad = new ArrayList<>();
                disponibilidad= recurso.getListaDisponibilidadRecursos();

                createStatement.executeUpdate("INSERT INTO recursosbancodetrabajo (nombre,descripcion,labid, bancoid) VALUES ('" + nombre + "', '" + descripcion + "', " + labID+ ", "  + bancoID+");",Statement.RETURN_GENERATED_KEYS);
                ResultSet prueba = createStatement.getGeneratedKeys();
                prueba.next();
                identificador=prueba.getInt(1);
                System.out.println("la fila es " + identificador );
                String patron = "/laboratorios/" + labID + "/bancos/" + bancoID + "/recursos/";
                String url = patron+identificador;
                createStatement.executeUpdate("UPDATE  recursosbancodetrabajo set url ='" + url + "' where id = "+ identificador + ";");

                for (LocalDateTime dis:disponibilidad) {

                    createStatement.executeUpdate("INSERT INTO disponibilidadrecursosbancodetrabajo (recursoid,disponibilidad) VALUES (" + identificador + ", '" + dis +  "');");
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

                String queryBBDD = "select recursosbancodetrabajo.id, recursosbancodetrabajo.url, recursosbancodetrabajo.nombre , recursosbancodetrabajo.descripcion, recursosbancodetrabajo.labid, recursosbancodetrabajo.bancoid, disponibilidadrecursosbancodetrabajo.disponibilidad from recursosbancodetrabajo inner join disponibilidadrecursosbancodetrabajo on recursosbancodetrabajo.id = disponibilidadrecursosbancodetrabajo.recursoid where recursosbancodetrabajo.id =" + id +" AND recursosbancodetrabajo.labid= " + labID + " AND recursosbancodetrabajo.bancoid= "+ bancoID+" ;";
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

                            if (mapa.containsKey(Integer.parseInt(rS.getString("recursosbancodetrabajo.id")))){
                                recurso=mapa.get(Integer.parseInt(rS.getString("recursosbancodetrabajo.id")));

                            }
                            else{

                                recurso = new RecursosBancoDeTrabajo();
                                recurso.setId(Integer.parseInt(rS.getString("recursosbancodetrabajo.id")));
                                recurso.setUrl(rS.getString("recursosbancodetrabajo.url"));
                                recurso.setNombreRecursoBanco(rS.getString("recursosbancodetrabajo.nombre"));
                                recurso.setDescripcionRecursoBanco(rS.getString("recursosbancodetrabajo.descripcion"));
                                recurso.setLabID(Integer.parseInt(rS.getString("recursosbancodetrabajo.labID")));
                                recurso.setBancoID(Integer.parseInt(rS.getString("recursosbancodetrabajo.bancoID")));


                                mapa.put(recurso.getId(), recurso);
                            }


                            LocalDateTime tiempo = rS.getObject("disponibilidadrecursosbancodetrabajo.disponibilidad",LocalDateTime.class);

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

    public Collection<RecursosBancoDeTrabajoShort> getAllRecursosBancosDeTrabajos(int labID, int bancoID) {

        HashMap<Integer,RecursosBancoDeTrabajoShort> mapa = new HashMap<>();

        try {
            if(conector()==true){
                 String queryBBDD = "select id, url, nombre ,descripcion, labid ,bancoid from recursosbancodetrabajo  WHERE recursosbancodetrabajo.bancoid= "+ bancoID+ " AND recursosbancodetrabajo.labid= " + labID + ";";
                int i=0;
                try {
                    rS = createStatement.executeQuery(queryBBDD);

                    while (rS.next()) {

                        RecursosBancoDeTrabajoShort recurso;

                        if (mapa.containsKey(Integer.parseInt(rS.getString("id")))){
                            recurso=mapa.get(Integer.parseInt(rS.getString("id")));
                        }
                        else{
                            recurso = new RecursosBancoDeTrabajoShort();
                            recurso.setId(Integer.parseInt(rS.getString("id")));
                            recurso.setUrl(rS.getString("url"));
                            recurso.setNombreRecursoBanco(rS.getString("nombre"));
                            recurso.setDescripcionRecursoBanco(rS.getString("descripcion"));
                            recurso.setLabID(Integer.parseInt(rS.getString("labid")));
                            recurso.setBancoID(Integer.parseInt(rS.getString("bancoid")));
                            mapa.put(recurso.getId(), recurso);
                        }


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
        return mapa.values();

    }

    public RecursosBancoDeTrabajo updateRecursosBancoDeTrabajo(RecursosBancoDeTrabajo recurso,int labID, int bancoID, int id) throws SQLException, ClassNotFoundException {
        try {
            if (conector() == true) {

                String nombre = recurso.getNombreRecursoBanco();
                String descripcion= recurso.getDescripcionRecursoBanco();


                String queryBBDD = "update recursosbancodetrabajo set nombre='" + nombre + "', descripcion ='" + descripcion + "'  where id="+id+" AND bancoid= " + bancoID+" AND labid= " + labID+ " ;";

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
        return getRecursosBancoDeTrabajo(labID, bancoID, id);
    }


    public ModifHoraria modifyRecurso(ModifHoraria mod, int labID, int bancoID, int id) throws SQLException, ClassNotFoundException {
        try {
            if (conector() == true) {
                switch (mod.getType()) {

                    case ADD:
                        //insert tabla disponibilidadBancoDeTrabajo con el id y la franja
                        LocalDateTime franja = mod.getFranja();
                        createStatement.executeUpdate("INSERT INTO disponibilidadrecursosbancodetrabajo (recursoid,disponibilidad) VALUES (" + id + ", '" + franja + "');");
                        break;

                    case REMOVE:
                        //delete tabla disponibilidadLaboratorio con el id y la franja
                        LocalDateTime franjaRemove = mod.getFranja();
                        createStatement.executeUpdate("delete from disponibilidadrecursosbancodetrabajo where recursoid="+id+" AND disponibilidad='" + franjaRemove+ "';");
                        break;


                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mod;
    }

    public boolean deleteRecursosBancoDeTrabajo(int labID, int bancoID, int id) throws SQLException, ClassNotFoundException {
        boolean valor= false;
        try {
            if (conector() == true) {

                String queryBBDD = "delete from recursosbancodetrabajo where id="+id+" AND bancoid = "+ bancoID + ";";

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

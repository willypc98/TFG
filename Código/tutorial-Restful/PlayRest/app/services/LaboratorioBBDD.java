package services;


import entities.*;

import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
        int identificador= -1;
        if (conector() == true) {
            con.setAutoCommit(false);
            try {

                String nombre = lab.getNombreLab();
                String descripcion = lab.getDescripcionLab();
                ArrayList<LocalDateTime> disponibilidad = new ArrayList<>();
                disponibilidad=  lab.getListaDisponibilidadLaboratorio();


                createStatement.executeUpdate("INSERT INTO laboratorio (nombre,descripcion) VALUES ('" + nombre + "', '" + descripcion + "');",Statement.RETURN_GENERATED_KEYS);
                ResultSet prueba = createStatement.getGeneratedKeys();
                prueba.next();
                identificador=prueba.getInt(1);
                System.out.println("la fila es " + identificador );
                String patron = "/laboratorios/";
                String url = patron+identificador;
                createStatement.executeUpdate("UPDATE  Laboratorio set url ='" + url + "' where id = "+ identificador + ";");

                for (LocalDateTime dis:disponibilidad) {

                    createStatement.executeUpdate("INSERT INTO DisponibilidadLaboratorio (labid,disponibilidad) VALUES (" + identificador + ", '" + dis +  "');");
                }
                con.commit();
                con.setAutoCommit(true);
                con.close();
            }
            catch(SQLException e){
                e.printStackTrace();
                con.rollback();
                }

        }
        //return lab;
        return getLaboratorio(identificador);
        //return url;
    }


    public Laboratorio getLaboratorio(int id) {

        HashMap<Integer,Laboratorio> mapa = new HashMap<>();
        try {
            if(conector()==true){

               String queryBBDD= "select laboratorio.id, laboratorio.url, laboratorio.nombre, laboratorio.descripcion, disponibilidadlaboratorio.disponibilidad from laboratorio inner join disponibilidadlaboratorio on laboratorio.id = disponibilidadlaboratorio.labid where laboratorio.id =" +id + " ;";
                String queryBBDD1= "select laboratorio.id, laboratorio.url, laboratorio.nombre, laboratorio.descripcion, bancodetrabajo.id as bancoID, bancodetrabajo.url as bancoURL, bancodetrabajo.descripcion as bancoDescripcion, bancodetrabajo.labid as bancoLabID from laboratorio INNER JOIN bancodetrabajo on laboratorio.id = bancodetrabajo.labid where laboratorio.id =" +id +  " ;";
                int i=0;

                try {

                    rS = createStatement.executeQuery(queryBBDD);

                } catch (SQLException ex) {
                    System.out.println("Falla esto 0");
                    ex.printStackTrace();
                    Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }


                if (rS == null){
                    System.out.println("la consulta esta vacia");
                    //lab= null;

                }
                else{

                    try {
                        //rS = createStatement.executeQuery(queryBBDD);
                        while (rS.next()) {

                            Laboratorio lab;

                            if (mapa.containsKey(Integer.parseInt(rS.getString("laboratorio.id")))){
                                lab=mapa.get(Integer.parseInt(rS.getString("laboratorio.id")));
                            }
                            else{
                                lab = new Laboratorio();
                                lab.setId(Integer.parseInt(rS.getString("laboratorio.id")));
                                lab.setUrl(rS.getString("laboratorio.url"));
                                lab.setNombreLab(rS.getString("laboratorio.nombre"));
                                lab.setDescripcionLab(rS.getString("laboratorio.descripcion"));
                                mapa.put(lab.getId(), lab);
                            }


                            LocalDateTime tiempo = rS.getObject("disponibilidadlaboratorio.disponibilidad",LocalDateTime.class);
                            lab.annadirListaDisponibilidad(tiempo);


                        }
                        rS1 = createStatement.executeQuery(queryBBDD1);
                        while(rS1.next()){
                            Laboratorio lab=null;
                            if (mapa.containsKey(Integer.parseInt(rS1.getString("laboratorio.id")))){
                                lab=mapa.get(Integer.parseInt(rS1.getString("laboratorio.id")));

                            }
                            BancoDeTrabajoShort banco = new BancoDeTrabajoShort();
                            banco.setId(Integer.parseInt(rS1.getString("bancoID")));
                            banco.setUrl(rS1.getString("bancoURL"));
                            banco.setDescripcionBanco(rS1.getString("bancoDescripcion"));
                            banco.setLabID(Integer.parseInt(rS1.getString("bancolabID")));

                            lab.annadirListaBancosDeTrabajo(banco);


                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        i = 0;
                        con.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                    }


                }

            }
            else{
                System.out.println("La conexión ha fallado");
                //lab=null;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (mapa.values().size() >0){


           return new ArrayList<>(mapa.values()).get(0);

        }
        else {
            return null;
        }


    }


    public Collection<LaboratorioShort> getAllLaboratorios() {

        HashMap<Integer,LaboratorioShort> mapa = new HashMap<>();

        try {
            if(conector()==true){
                String queryBBDD= "select id, url, nombre, descripcion from laboratorio";
                int i=0;
                try {
                    rS = createStatement.executeQuery(queryBBDD);
/*
                    while (rS.next()) {

                        Laboratorio lab= getLaboratorio(Integer.parseInt(rS.getString("id")));
                        mapa.put(lab.getId(), lab);

                    }


 */
                    while(rS.next()){
                        LaboratorioShort lab;

                        if (mapa.containsKey(Integer.parseInt(rS.getString("id")))){
                            lab=mapa.get(Integer.parseInt(rS.getString("id")));
                        }
                        else{
                            lab = new LaboratorioShort();
                            lab.setId(Integer.parseInt(rS.getString("id")));
                            lab.setUrl(rS.getString("url"));
                            lab.setNombreLab(rS.getString("nombre"));
                            lab.setDescripcionLab(rS.getString("descripcion"));
                            mapa.put(lab.getId(), lab);
                        }
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
                //return new ArrayList<>(mapa.values);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println("El tamaño de la lista es" + mapa.values().size());
        return mapa.values();

    }

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
                return null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lab;
    }

public  ModifHoraria modifyLaboratorio(ModifHoraria mod, int id) throws SQLException, ClassNotFoundException {
        try {
        if (conector() == true) {
            switch (mod.getType()) {

                case ADD:
                    //insert tabla disponibilidadLaboratorio con el id y la franja
                    LocalDateTime franja = mod.getFranja();
                    createStatement.executeUpdate("INSERT INTO disponibilidadlaboratorio (labid,disponibilidad) VALUES (" + id + ", '" + franja + "');");
                    break;

                case REMOVE:
                    //delete tabla disponibilidadLaboratorio con el id y la franja
                    LocalDateTime franjaRemove = mod.getFranja();
                    createStatement.executeUpdate("delete from disponibilidadlaboratorio where labid="+id+" AND disponibilidad='" + franjaRemove+ "';");
                    break;


            }

        }
    } catch (SQLException ex) {
        Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
    }

    return mod;
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

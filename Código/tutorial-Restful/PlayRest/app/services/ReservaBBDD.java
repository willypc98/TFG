package services;

import entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservaBBDD extends ConexionBBDD{

    private static ReservaBBDD instance;
    public static ReservaBBDD getInstance() {
        if (instance == null) {
            instance = new ReservaBBDD();
        }
        return instance;
    }
    /*
    public Reserva addReserva(Reserva reserva) throws SQLException, ClassNotFoundException {
        int usuID=0;  int labID =0; int bancoID=0;
        int identificador= -1;
        if (conector() == true) {
            con.setAutoCommit(false);
            try {

               Usuario usu = reserva.getUsu();
               Laboratorio lab= reserva.getLab();
               BancoDeTrabajo ban =  reserva.getBan();
               usuID= usu.getId();
               labID= lab.getId();
               bancoID= ban.getId();

              LocalDateTime horario= reserva.getDisponibilidadReserva();


              ArrayList<RecursosBancoDeTrabajo> recursos= new ArrayList<>();
              recursos = reserva.getListaRecursos();


                createStatement.executeUpdate("INSERT INTO Reserva (usuID,labID,bancoID, horario) VALUES (" + usuID + ", " + labID+ ", "  + bancoID+" ,'" + horario+ " );", Statement.RETURN_GENERATED_KEYS);
                ResultSet prueba = createStatement.getGeneratedKeys();
                prueba.next();
                identificador=prueba.getInt(1);
                System.out.println("la fila es " + identificador );
                //String patron = "/laboratorios/" + labID + "/bancos/" + bancoID + "/recursos/";
                String url = "/reservas/"+identificador;
                createStatement.executeUpdate("UPDATE  Reserva set url ='" + url + "' where id = "+ identificador + ";");

                for (RecursosBancoDeTrabajo recurso:recursos) {
                       int recursoID= recurso.getId();
                    createStatement.executeUpdate("INSERT INTO reservarecursos (reservaID,recursoID) VALUES (" + identificador + ", '" + recursoID +  "');");
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
        return getReserva(usuID,labID, bancoID , identificador);
    }

    public Reserva getReserva(int usuID, int labID, int bancoID , int id) {

        HashMap<Integer,Reserva> mapa = new HashMap<>();
        try {
            if(conector()==true){

                String queryBBDD = "select Reserva.id, Reserva.url, Reserva.usuID , Reserva.labID, Reserva.bancoID, reservarecursos.recursoID as recursosID from Reserva inner join reservarecursos on Reserva.id = reservarecursos.recursoID where Reserva.id =" + id +" AND Reserva.usuID= " + usuID + " AND Reserva.labID= " + labID + " AND Reserva.bancoID= "+ bancoID+" ;";
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
                            Reserva reserva;

                            if (mapa.containsKey(Integer.parseInt(rS.getString("Reserva.id")))){
                                reserva=mapa.get(Integer.parseInt(rS.getString("Reserva.id")));

                            }
                            else{

                                reserva = new Reserva();
                                reserva.setId(Integer.parseInt(rS.getString("Reserva.id")));
                                reserva.setUrl(rS.getString("Reserva.url"));

                                reserva.setUsu(rS.getString("Reserva.usuID"));
                                reserva.setLab(Integer.parseInt(rS.getString("Reserva.labID")));
                                reserva.setBan(Integer.parseInt(rS.getString("Reserva.bancoID")));


                                mapa.put(reserva.getId(), reserva);
                            }


                            RecursosBancoDeTrabajo recurso = new RecursosBancoDeTrabajo();
                            recurso.setId(Integer.parseInt(rS1.getString("recursosID")));


                            reserva.annadirListaRecursos(recurso);





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

    public boolean deleteReserva(int id){

        boolean valor= false;

        try {
            if (conector() == true) {

                con.setAutoCommit(false);

                Usuario usu = reserva.getUsu();
                Laboratorio lab= reserva.getLab();
               int labID= lab.getId();
                BancoDeTrabajo ban =  reserva.getBan();
                int bancoID= ban.getId();
                ArrayList<RecursosBancoDeTrabajo> recursos= new ArrayList<>();
                recursos = reserva.getListaRecursos();
                LocalDateTime franja = reserva.getHorario();




                String queryBBDD = "INSERT INTO disponibilidadlaboratorio (labid,disponibilidad) VALUES (" + labID + ", '" + franja + "');";
                String queryBBDD1= "INSERT INTO disponibilidadBancoDeTrabajo (bancoID,disponibilidad) VALUES (" + bancoID + ", '" + franja + "');";

                String queryBBDD2 = "delete from reserva where id="+id+";";

                try {
                    createStatement.executeUpdate(queryBBDD);
                    createStatement.executeUpdate(queryBBDD1);
                    for (RecursosBancoDeTrabajo recurso:recursos) {
                        int recursoID= recurso.getId();
                        createStatement.executeUpdate("INSERT INTO disponibilidadlaboratorioRecursosBancoDeTRabajo (recursoID,disponibilidad) VALUES (" + recursoID + ", '" + franja + "');");
                    }
                    createStatement.executeUpdate(queryBBDD2);

                    con.commit();
                    con.setAutoCommit(true);
                    con.close();
                    valor = true;
                    return valor;


                } catch (SQLException ex) {
                    Logger.getLogger(LaboratorioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                    con.rollback();
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



     */
    }


package services;

import entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
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

    public Reserva addReserva(Reserva reserva) throws SQLException, ClassNotFoundException {
        int usuID=0;  int labID =0; int bancoID=0;
        int identificador= -1; int numberRow=-1;int numberRowLab= -1; int contadorRecursos=0;
        if (conector() == true) {
            con.setAutoCommit(false);
            try {

               Usuario usu = reserva.getUsu();
               LaboratorioShort lab= reserva.getLab();
               BancoDeTrabajoShort ban =  reserva.getBan();
               usuID= usu.getId();
               System.out.println("El ID del Usuario es el siguiente" +usuID);
               labID= lab.getId();
               System.out.println("El ID del Laboratorio es el siguiente" +labID);
               bancoID= ban.getId();
               System.out.println("El ID del Banco es el siguiente" +bancoID);

              LocalDateTime horario= reserva.getDisponibilidadReserva();
              System.out.println("El horario de la reserva es el siguiente" +horario);


              ArrayList<RecursosBancoDeTrabajoShort> recursos= new ArrayList<>();
                System.out.println("El tamaño de la lista de recursos es: " + recursos.size());
              recursos = reserva.getListaRecursos();
                System.out.println("El tamaño de la lista de recursos es: " + recursos.size());

              String queryBBDD1=("select count(*) from usuario where id="+ usuID +";");
              String queryBBDD2=("select count(*) from disponibilidadlaboratorio where labID="+ labID +" AND disponibilidad = '"+ horario + "';");
              String queryBBDD3=("delete from disponibilidadbancodetrabajo where bancoid="+ bancoID +" AND disponibilidad = '"+ horario + "';");

                rS=createStatement.executeQuery(queryBBDD1);
                while (rS.next()){
                    numberRow =rS.getInt("count(*)");
                }
                if(numberRow==1){
                    System.out.println("El usuario está bien");
                    rS=createStatement.executeQuery(queryBBDD2);
                    while (rS.next()){
                        numberRowLab =rS.getInt("count(*)");
                    }
                    if(numberRowLab==1){
                        System.out.println("El laboratorio está bien");
                        createStatement.executeUpdate(queryBBDD3);
                        if(createStatement.getUpdateCount()==1){
                            System.out.println("El banco de trabajo está bien");
                            for (RecursosBancoDeTrabajoShort recurso:recursos) {
                                int recursoID= recurso.getId();
                                System.out.println("El tamaño de la lista de recursos es: " + recursos.size());
                                System.out.println("El ID del recurso es el siguiente " + recursoID );
                                createStatement.executeUpdate("delete from disponibilidadrecursosbancodetrabajo where recursoid="+ recursoID +" AND disponibilidad = '"+ horario + "';");
                               if(createStatement.getUpdateCount()==1){
                                   contadorRecursos++;
                               }
                                System.out.println("el tamaño del contador es " + contadorRecursos);
                            }
                            if(contadorRecursos==recursos.size()){



                                createStatement.executeUpdate("INSERT INTO reserva (usuarioid,labid,bancoid, disponibilidad) VALUES (" + usuID + ", " + labID+ ", "  + bancoID+" ,'" + horario+ "' );", Statement.RETURN_GENERATED_KEYS);
                                ResultSet prueba = createStatement.getGeneratedKeys();
                                prueba.next();
                                identificador=prueba.getInt(1);
                                System.out.println("la fila es " + identificador );
                                String url = "/reservas/"+identificador;
                                createStatement.executeUpdate("UPDATE  reserva set url ='" + url + "' where id = "+ identificador + ";");

                                for (RecursosBancoDeTrabajoShort recurso:recursos) {
                                    int recursoID= recurso.getId();
                                    createStatement.executeUpdate("INSERT INTO reservarecursos (reservaid,recursoid) VALUES (" + identificador + ", " + recursoID +  ");");
                                }

                                con.commit();
                                con.setAutoCommit(true);
                                con.close();




                            }
                            else {
                                reserva=null;
                                System.out.println("Algún recurso del banco de trabajo o la disponibilidad que busca no existe");
                                con.rollback();
                            }

                        }
                        else {
                            reserva=null;
                            System.out.println("El banco de trabajo o la disponibilidad que busca no existe");
                            con.rollback();
                        }
                    }
                    else {
                        reserva=null;
                        System.out.println("El laboratorio o la disponibilidad que busca no existe");
                        con.rollback();
                    }

                }
                else {

                    reserva=null;
                    System.out.println("El usuario que busca no existe");
                    con.rollback();
                }





            }

            catch(SQLException e){
                e.printStackTrace();
                con.rollback();
            }

        }
        //return reserva;
        return getReserva(identificador);
    }

    public Reserva getReserva(int id) {
    int usuarioID = 0; int labID=0; int bancoID=0; int recursoID=0;
    String usuarioURL; String labURL; String bancoURL; String recursoURL;

        HashMap<Integer,Reserva> mapa = new HashMap<>();
        try {
            if(conector()==true){
                String queryBBDD= "select reserva.id, reserva.url, reserva.usuarioid , reserva.labid, reserva.bancoid,reserva.disponibilidad, reservarecursos.recursoid as recursosID, usuario.url as usuarioURL, laboratorio.url as labURL, bancodetrabajo.url as bancoURL, recursosbancodetrabajo.url as recursoURL from reserva inner join reservarecursos on reserva.id = reservarecursos.reservaid inner join usuario on reserva.usuarioid  =usuario.id inner join laboratorio on reserva.labID =laboratorio.id inner join bancodetrabajo on reserva.bancoid =bancodetrabajo.id inner join recursosbancodetrabajo on reservarecursos.recursoid =recursosbancodetrabajo.id where reserva.id =" + id+" ;";
                int i=0;

                try {

                    rS = createStatement.executeQuery(queryBBDD);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(ReservaBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (rS == null){
                    //banco= null;

                }
                else{

                    try {

                        while (rS.next()) {

                            Reserva reserva;

                            if (mapa.containsKey(Integer.parseInt(rS.getString("reserva.id")))){

                                reserva=mapa.get(Integer.parseInt(rS.getString("reserva.id")));

                            }
                            else{
                                System.out.println("Nueva Reservas ");
                                reserva = new Reserva();
                                Usuario usu = new Usuario();
                                LaboratorioShort lab= new LaboratorioShort();
                                BancoDeTrabajoShort banco=new BancoDeTrabajoShort();

                                reserva.setId(Integer.parseInt(rS.getString("reserva.id")));
                                reserva.setUrl(rS.getString("reserva.url"));
                                usuarioID=Integer.parseInt(rS.getString("reserva.usuarioid"));
                                usu.setId(usuarioID);
                                usuarioURL=rS.getString("usuarioURL");
                                usu.setUrl(usuarioURL);
                                reserva.setUsu(usu);

                                labID=Integer.parseInt(rS.getString("reserva.labid"));
                                lab.setId(labID);
                                labURL=rS.getString("labURL");
                                lab.setUrl(labURL);
                                reserva.setLab(lab);
                                bancoID=Integer.parseInt(rS.getString("reserva.bancoid"));
                                banco.setId(bancoID);
                                bancoURL=rS.getString("bancoURL");
                                banco.setUrl(bancoURL);
                                reserva.setBan(banco);
                                LocalDateTime horario=rS.getObject("reserva.disponibilidad",LocalDateTime.class);
                                reserva.setDisponibilidadReserva(horario);


                                mapa.put(reserva.getId(), reserva);
                            }



                            RecursosBancoDeTrabajoShort recurso = new RecursosBancoDeTrabajoShort();
                            recurso.setId(Integer.parseInt(rS.getString("recursosID")));
                            recurso.setUrl(rS.getString("recursoURL"));


                            reserva.annadirListaRecursos(recurso);






                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        Logger.getLogger(ReservaBBDD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        i = 0;
                        con.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        Logger.getLogger(ReservaBBDD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
            else{
               //reserva=null;


            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ReservaBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(ReservaBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (mapa.values().size() >0){



            return new ArrayList<>(mapa.values()).get(0);

        }
        else {

            return null;

        }


    }

    public Collection<Reserva> getAllReservas() {
        int usuarioID = 0; int labID=0; int bancoID=0; int recursoID=0;


        HashMap<Integer,Reserva> mapa = new HashMap<>();
        try {
            if(conector()==true){

                 String queryBBDD= "select reserva.id, reserva.url, reserva.usuarioid , reserva.labid, reserva.bancoid,reserva.disponibilidad, reservarecursos.recursoid as recursosID from reserva inner join reservarecursos on reserva.id = reservarecursos.reservaid ;";
                int i=0;

                try {

                    rS = createStatement.executeQuery(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(ReservaBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (rS == null){
                    //banco= null;

                }
                else{

                    try {
                        while (rS.next()) {
                            Reserva reserva;

                            if (mapa.containsKey(Integer.parseInt(rS.getString("reserva.id")))){
                                reserva=mapa.get(Integer.parseInt(rS.getString("reserva.id")));

                            }
                            else{

                                reserva = new Reserva();
                                Usuario usu = new Usuario();
                                LaboratorioShort lab= new LaboratorioShort();
                                BancoDeTrabajoShort banco=new BancoDeTrabajoShort();

                                reserva.setId(Integer.parseInt(rS.getString("reserva.id")));
                                reserva.setUrl(rS.getString("reserva.url"));
                                usuarioID=Integer.parseInt(rS.getString("reserva.usuarioid"));
                                usu.setId(usuarioID);
                                reserva.setUsu(usu);

                                labID=Integer.parseInt(rS.getString("reserva.labid"));
                                lab.setId(labID);
                                reserva.setLab(lab);
                                bancoID=Integer.parseInt(rS.getString("reserva.bancoid"));
                                banco.setId(bancoID);
                                reserva.setBan(banco);
                                LocalDateTime horario=rS.getObject("reserva.disponibilidad",LocalDateTime.class);
                                reserva.setDisponibilidadReserva(horario);


                                mapa.put(reserva.getId(), reserva);
                            }


                            RecursosBancoDeTrabajoShort recurso = new RecursosBancoDeTrabajoShort();
                            recurso.setId(Integer.parseInt(rS.getString("recursosid")));


                            reserva.annadirListaRecursos(recurso);





                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ReservaBBDD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        i = 0;
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ReservaBBDD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
            else{
                //reserva=null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservaBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReservaBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapa.values();


    }


    public boolean deleteReserva(int id){

        boolean valor= false;

        try {
            if (conector() == true) {

                con.setAutoCommit(false);

                Reserva reserva = new Reserva();
                Usuario usu = new Usuario();
                LaboratorioShort lab= new LaboratorioShort();
                BancoDeTrabajoShort banco=new BancoDeTrabajoShort();

                reserva=getReserva(id);



                usu = reserva.getUsu();
                System.out.println("El ID del recurso " +usu.getId());
                lab= reserva.getLab();
                int labID= lab.getId();
                System.out.println("El ID del lab " + labID);
                banco =  reserva.getBan();
                int bancoID= banco.getId();
                System.out.println("El ID del recurso" + bancoID);
                ArrayList<RecursosBancoDeTrabajoShort> recursos= new ArrayList<>();
                recursos = reserva.getListaRecursos();
                System.out.println("El tamaño de la lista de recursos es " + recursos.size());
                LocalDateTime horario = reserva.getDisponibilidadReserva();
                System.out.println("El horario de la reserva" + horario);




              //  String queryBBDD = "INSERT INTO disponibilidadlaboratorio (labID,disponibilidad) VALUES (" + labID + ", '" + horario + "');";
                String queryBBDD1= "INSERT INTO disponibilidadbancodetrabajo (bancoid,disponibilidad) VALUES (" + bancoID + ", '" + horario + "');";

                System.out.println(queryBBDD1);
                String queryBBDD2 = "delete from reserva where id="+id+";";
                System.out.println(queryBBDD2);

                if(conector()==true) {
                    try {
                        con.setAutoCommit(false);

                        System.out.println("Antes de la query");
                        createStatement.executeUpdate(queryBBDD1);
                        System.out.println("Después de la query");
                        for (RecursosBancoDeTrabajoShort recurso : recursos) {
                            int recursoID = recurso.getId();
                            System.out.println("El ID del recurso" + recursoID);
                            createStatement.executeUpdate("INSERT INTO disponibilidadrecursosbancodetrabajo (recursoid,disponibilidad) VALUES (" + recursoID + ", '" + horario + "');");
                        }
                        createStatement.executeUpdate(queryBBDD2);

                        con.commit();
                        con.setAutoCommit(true);
                        con.close();
                        valor = true;
                        //return valor;


                    } catch (SQLException ex) {
                        Logger.getLogger(ReservaBBDD.class.getName()).log(Level.SEVERE, null, ex);
                        con.rollback();
                    }
                }

                try {

                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReservaBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            else{

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservaBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReservaBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }




    }


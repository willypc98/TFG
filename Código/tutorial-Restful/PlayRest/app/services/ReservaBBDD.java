package services;

import entities.Reserva;
import entities.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
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
        if (conector() == true) {

            int id = reserva.getId();
            String url = reserva.getUrl();
            String nombre= reserva.getNombre();
            String grado= reserva.getGrado();


            createStatement.executeUpdate("INSERT INTO reserva (id,url,nombre,grado) VALUES ("+id+", '" + url + "', '" + nombre + "', '" + grado + "')");
            con.close();

        }
        return reserva;
    }

    public Reserva getReserva(int id) {
        Reserva reserva = new Reserva();
        try {
            if(conector()==true){

                String queryBBDD = "select * from reserva where id=" + id + ";";
                int i=0;
                try {
                    rS = createStatement.executeQuery(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(ReservaBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (rS == null){
                    reserva= null;

                }
                else{

                    try {
                        while (rS.next()) {
                            reserva.setId(rS.getInt("id"));
                            reserva.setUrl(rS.getString("url"));
                            reserva.setNombre(rS.getString("nombre"));
                            reserva.setGrado(rS.getString("grado"));


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
                reserva=null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reserva;
    }

    public ArrayList<Reserva> getAllReservas() {
        ArrayList<Reserva> reservasLista = new ArrayList();
        try {
            if(conector()==true){
                String queryBBDD = "select * from reserva;";
                int i=0;
                try {
                    rS = createStatement.executeQuery(queryBBDD);

                    while (rS.next()) {
                        Reserva reserva = new Reserva();
                        reserva.setId(Integer.parseInt(rS.getString("id")));
                        reserva.setUrl(rS.getString("url"));
                        reserva.setNombre(rS.getString("nombre"));
                        reserva.setGrado(rS.getString("grado"));
                        reservasLista.add(reserva);

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ReservaBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    i=0;
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReservaBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            else{
                return reservasLista;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("El tamaño de la lista es" + reservasLista.size());
        return reservasLista;

    }
    public Reserva updateReserva(Reserva reserva ) throws SQLException, ClassNotFoundException {
        try {
            if (conector() == true) {
                int id = reserva.getId();
                //String url = reserva.getUrl();
                String nombre = reserva.getNombre();
                String grado= reserva.getGrado();

                String queryBBDD = "update Reserva set nombre='"+nombre+"', grado='"+grado+"' where id="+id+";";

                try {
                    createStatement.executeUpdate(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(ReservaBBDD.class.getName()).log(Level.SEVERE, null, ex);
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
        return reserva;
    }


    public boolean deleteReserva(int id) throws SQLException, ClassNotFoundException {
        boolean valor= false;
        try {
            if (conector() == true) {

                String queryBBDD = "delete from reserva where id="+id+";";

                try {
                    createStatement.executeUpdate(queryBBDD);
                    valor = true;
                    return valor;
                } catch (SQLException ex) {
                    Logger.getLogger(ReservaBBDD.class.getName()).log(Level.SEVERE, null, ex);
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
    */
 
}

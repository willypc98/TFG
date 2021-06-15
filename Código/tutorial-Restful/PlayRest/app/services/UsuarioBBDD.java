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

public class UsuarioBBDD extends ConexionBBDD{

    private static UsuarioBBDD instance;
    public static UsuarioBBDD getInstance() {
        if (instance == null) {
            instance = new UsuarioBBDD();
        }
        return instance;
    }

    public Usuario addUsuario(Usuario usu) throws SQLException, ClassNotFoundException {
        int identificador= -1;

        if (conector() == true) {


            String nombre= usu.getNombre();
            String grado= usu.getGrado();


            createStatement.executeUpdate("INSERT INTO usuario (nombre,grado) VALUES ('" + nombre + "', '" + grado + "');" , Statement.RETURN_GENERATED_KEYS);
            ResultSet prueba = createStatement.getGeneratedKeys();
            prueba.next();
            identificador=prueba.getInt(1);
            System.out.println("la fila es " + identificador );
            String patron = "/usuarios/";
            String url = patron+identificador;
            createStatement.executeUpdate("UPDATE  usuario set url ='" + url + "' where id = "+ identificador + ";");


            con.close();

        }
        return getUsuario(identificador);
    }

    public Usuario getUsuario(int id) {
        //Usuario usu = new Usuario();
        HashMap<Integer,Usuario> mapa = new HashMap<>();
        try {
            if(conector()==true){
                System.out.println("Antes de guardar la query");
                String queryBBDD = "select usuario.id, usuario.url,usuario.nombre, usuario.grado, reserva.id as ReservaID, reserva.url as ReservaURL, reserva.disponibilidad as Disponibilidad from usuario inner join reserva on usuario.id = reserva.usuarioID where usuario.id=" + id + ";";
                int i=0;
                try {
                    rS = createStatement.executeQuery(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (rS == null){
                   // usu= null;

                }
                else{

                    try {
                        while (rS.next()) {
                            Usuario usu;
                            if (mapa.containsKey(Integer.parseInt(rS.getString("usuario.id")))){
                                usu=mapa.get(Integer.parseInt(rS.getString("usuario.id")));
                            }
                            else{

                                usu = new Usuario();
                                usu.setId(rS.getInt("usuario.id"));

                                usu.setUrl(rS.getString("usuario.url"));

                                usu.setNombre(rS.getString("usuario.nombre"));

                                usu.setGrado(rS.getString("usuario.grado"));

                                mapa.put(usu.getId(), usu);
                            }

                            ReservaShort reserva = new ReservaShort();
                            reserva.setId(Integer.parseInt(rS.getString("ReservaID")));
                            reserva.setUrl(rS.getString("ReservaURL"));
                            reserva.setDisponibilidadReserva(rS.getObject("Disponibilidad",LocalDateTime.class));

                            usu.annadirReservas(reserva);





                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        i = 0;
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
            else{
               // usu=null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (mapa.values().size() >0){


            return new ArrayList<>(mapa.values()).get(0);

        }
        else {
            return null;
        }
    }

    public ArrayList<Usuario> getAllUsuarios() {
        ArrayList<Usuario> usuariosLista = new ArrayList();
        try {
            if(conector()==true){
                String queryBBDD = "select * from usuario;";
                int i=0;
                try {
                    rS = createStatement.executeQuery(queryBBDD);

                    while (rS.next()) {
                        Usuario usu = new Usuario();
                        usu.setId(Integer.parseInt(rS.getString("id")));
                        usu.setUrl(rS.getString("url"));
                        usu.setNombre(rS.getString("nombre"));
                        usu.setGrado(rS.getString("grado"));
                        usuariosLista.add(usu);

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    i=0;
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            else{
                return usuariosLista;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("El tamaño de la lista es" + usuariosLista.size());
        return usuariosLista;

    }
    public Usuario updateUsuario(Usuario usu, int id ) throws SQLException, ClassNotFoundException {
        try {
            if (conector() == true) {
                //int id = usu.getId();
                //String url = usu.getUrl();
                String nombre = usu.getNombre();
                String grado= usu.getGrado();

                String queryBBDD = "update Usuario set nombre='"+nombre+"', grado='"+grado+"' where id="+id+";";

                try {
                    createStatement.executeUpdate(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {

                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getUsuario(id);
    }


    public boolean deleteUsuario(int id) throws SQLException, ClassNotFoundException {
        boolean valor= false;
        try {
            if (conector() == true) {

                String queryBBDD = "delete from usuario where id="+id+";";

                try {
                    createStatement.executeUpdate(queryBBDD);
                    valor = true;
                    return valor;
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {

                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }
}

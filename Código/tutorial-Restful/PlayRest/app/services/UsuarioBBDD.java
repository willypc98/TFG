package services;

import entities.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
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
        if (conector() == true) {

            int id = usu.getId();
            String url = usu.getUrl();
            String nombre= usu.getNombre();
            String grado= usu.getGrado();


            createStatement.executeUpdate("INSERT INTO usuario (id,url,nombre,grado) VALUES ("+id+", '" + url + "', '" + nombre + "', '" + grado + "')");
            con.close();

        }
        return usu;
    }

    public Usuario getUsuario(int id) {
        Usuario usu = new Usuario();
        try {
            if(conector()==true){

                String queryBBDD = "select * from usuario where id=" + id + ";";
                int i=0;
                try {
                    rS = createStatement.executeQuery(queryBBDD);
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (rS == null){
                    usu= null;

                }
                else{

                    try {
                        while (rS.next()) {
                            usu.setId(rS.getInt("id"));
                            usu.setUrl(rS.getString("url"));
                            usu.setNombre(rS.getString("nombre"));
                            usu.setGrado(rS.getString("grado"));


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
                usu=null;

            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usu;
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
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeBBDD.class.getName()).log(Level.SEVERE, null, ex);
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
        return usu;
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

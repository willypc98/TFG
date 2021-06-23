package services;

import play.db.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBBDD {

    protected static Connection con;
    protected static final String driver="com.mysql.jdbc.Driver";
    protected static final String user="root";
    protected static final String pass="root";
    protected static final String url="jdbc:mysql://172.17.0.2:3306?useSSL=false";


    protected Statement createStatement;

    protected ResultSet rS;
    protected ResultSet rS1;

    protected boolean conector() throws SQLException, ClassNotFoundException {
        // Reseteamos a null la conexion a la bd
        con=null;
        boolean valor=false;
        try{

            int i=0;
            //  String valor= "fallo";
            Class.forName(driver);
            // Nos conectamos a la bd
            con= (com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, pass);
            // Si la conexion fue exitosa mostramos un mensaje de conexion exitosa
            if (con!=null){

                createStatement = con.createStatement();
                String creacion = "create database if not exists tfg;";
                String uso = "use tfg;";
                String creacionUsuario = "create table if not exists usuario(\n" +
                        "id int (100) auto_increment not null,\n" +
                        "url varchar (40),\n" +
                        "nombre varchar (40) not null,\n" +
                        "grado varchar (20) not null,\n" +
                        "primary key(id)\n" +
                        " );";
                String creacionLaboratorio = "create table if not exists laboratorio(\n" +
                        "id int (100) auto_increment not null,\n" +
                        "url varchar (40),\n" +
                        "nombre varchar (40) not null,\n" +
                        "descripcion varchar (100) not null,\n" +
                        "primary key(id)\n" +
                        " );";
                String creacionBanco = "create table if not exists bancodetrabajo(\n" +
                        "id int (100) auto_increment not null,\n" +
                        "url varchar (40),\n" +
                        "descripcion varchar (100) not null,\n" +
                        "labid int,\n" +
                        "primary key(id),\n" +
                        "foreign key (labid) references laboratorio(id) on delete cascade\n" +
                        " );\n";
                String creacionRecurso = "create table if not exists recursosbancodetrabajo(\n" +
                        "id int (100) auto_increment not null,\n" +
                        "url varchar (40),\n" +
                        "nombre varchar (40) not null,\n" +
                        "descripcion varchar (100) not null,\n" +
                        "labid int,\n" +
                        "bancoid int,\n" +
                        "primary key(id),\n" +
                        "foreign key (labid) references laboratorio(id) on delete cascade,\n" +
                        "foreign key (bancoid) references bancodetrabajo(id) on delete cascade\n" +
                        " );";
                String creacionReserva = "create table if not exists reserva(\n" +
                        "id int (100) auto_increment not null,\n" +
                        "url varchar (40),\n" +
                        "usuarioid int,\n" +
                        "labid int,\n" +
                        "bancoid int,\n" +
                        "disponibilidad datetime,\n" +
                        "primary key(id),\n" +
                        "foreign key (usuarioid) references usuario(id) on delete cascade,\n" +
                        "foreign key (labid) references laboratorio(id) on delete cascade ,\n" +
                        "foreign key (bancoid) references bancodetrabajo(id) on delete cascade\n" +
                        " );";
                String creacionReservaRecursos = "create table if not exists reservarecursos(\n" +
                        "reservaid int,\n" +
                        "recursoid int,\n" +
                        "foreign key (reservaid) references reserva(id) on delete cascade,\n" +
                        "foreign key (recursoid) references recursosbancodetrabajo(id) on delete cascade,\n" +
                        "primary key (reservaid,recursoid)\n" +
                        ");";
                String creacionDisLab = "create table if not exists disponibilidadlaboratorio(\n" +
                        "labid int,\n" +
                        "disponibilidad datetime,\n" +
                        "foreign key (labid) references laboratorio(id) on delete cascade,\n" +
                        "primary key (labid,disponibilidad)\n" +
                        ");";
                String creacionDisBanco = "create table if not exists disponibilidadbancodetrabajo(\n" +
                        "bancoid int,\n" +
                        "disponibilidad datetime,\n" +
                        "foreign key (bancoid) references bancodetrabajo(id) on delete cascade,\n" +
                        "primary key (bancoid,disponibilidad)\n" +
                        ");";
                String creacionDisRecurso = "create table if not exists disponibilidadrecursosbancodetrabajo(\n" +
                        "recursoid int,\n" +
                        "disponibilidad datetime,\n" +
                        "foreign key (recursoid) references recursosbancodetrabajo(id) on delete cascade,\n" +
                        "primary key (recursoid,disponibilidad)\n" +
                        ");";


                createStatement.executeUpdate(creacion);

                createStatement.executeUpdate(uso);

                createStatement.executeUpdate(creacionUsuario);

                createStatement.executeUpdate(creacionLaboratorio);

                createStatement.executeUpdate(creacionBanco);

                createStatement.executeUpdate(creacionRecurso);

                createStatement.executeUpdate(creacionReserva);

                createStatement.executeUpdate(creacionReservaRecursos);

                createStatement.executeUpdate(creacionDisLab);

                createStatement.executeUpdate(creacionDisBanco);

                createStatement.executeUpdate(creacionDisRecurso);

                valor= true;
            }
        }
        //Si la conexion NO fue exitosa mostramos un mensaje de error
        catch (ClassNotFoundException | SQLException e){
            System.out.println( e);
        }
        return  valor;
    }
}
